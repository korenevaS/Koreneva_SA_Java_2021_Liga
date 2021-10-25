package com.github.korenevaS.queue.service;


import com.github.korenevaS.queue.controller.model.RequestReceipt;
import com.github.korenevaS.queue.error.NoReceiptException;
import com.github.korenevaS.queue.error.ReceiptException;
import com.github.korenevaS.queue.error.ReceiptIsAlreadyConfirmedException;
import com.github.korenevaS.queue.error.ReceiptIsTimedOutException;
import com.github.korenevaS.queue.repository.ReceiptRepository;
import com.github.korenevaS.queue.repository.UserRepository;
import com.github.korenevaS.queue.repository.model.Receipt;
import com.github.korenevaS.queue.repository.model.Timetable;
import com.github.korenevaS.queue.repository.model.User;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
public class ReceiptService {
    private final Timetable timetable = new Timetable();
    private final ReceiptRepository receiptRepository;
    private final UserRepository userRepository;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    private final int fifteenMinutesInSeconds = 60 * 15;
    private final int receiptTimeout = fifteenMinutesInSeconds;

    public ReceiptService(ReceiptRepository receiptRepository, UserRepository userRepository) {
        this.receiptRepository = receiptRepository;
        this.userRepository = userRepository;
    }

    @SneakyThrows
    public Calendar parseFromStringToCalendar(String dateString) {
        Date desiredDate = dateFormat.parse(dateString);
        Calendar c = Calendar.getInstance();
        c.setTime(desiredDate);
        return c;
    }

    public Integer getDayOfWeek(String dateString) {
        Calendar c = parseFromStringToCalendar(dateString);
        return c.get(Calendar.DAY_OF_WEEK);
    }

    public boolean checkDateAccuracy(String dateString) {
        Calendar currentDate = Calendar.getInstance();
        currentDate.add(Calendar.DAY_OF_MONTH, -1);
        Calendar c = parseFromStringToCalendar(dateString);
        return c.before(currentDate);
    }

    public boolean checkTime(String timeString) {
        List<String> timetableForDay = timetable.getTimetable().get(2);
        System.out.println(timetable.getTimetable().get(2));
        return timetableForDay.stream().anyMatch(timeString::equals);
    }

    private List<Receipt> getReceiptForDay(String dateString) {
        Calendar currentDate = parseFromStringToCalendar(dateString);
        Calendar endCurrentDate = parseFromStringToCalendar(dateString);
        endCurrentDate.add(Calendar.DAY_OF_MONTH, 1);
        endCurrentDate.add(Calendar.SECOND, -1);
        return receiptRepository.findAllByStartOfReceptionLessThanEqualAndStartOfReceptionGreaterThanEqual(endCurrentDate, currentDate);
    }

    public List<String> listOfFreeSlots(String dateString) {
        removeObsoleteReceipts();
        if (!checkDateAccuracy(dateString)) {
            List<String> timetableForDay = timetable.getTimetable().get(getDayOfWeek(dateString));
            if (timetableForDay.isEmpty()) {
                throw new ReceiptException("Выходной день.");
            } else {
                List<Receipt> employedSlots = getReceiptForDay(dateString);
                employedSlots.forEach(slot -> {
                            int slotHour = slot.getStartOfReception().get(Calendar.HOUR_OF_DAY);
                            String time = slotHour + ":00";
                            timetableForDay.removeIf(freeSlotsFromTimetable -> freeSlotsFromTimetable.equals(time));
                        }
                );
                return timetableForDay;
            }
        } else {
            throw new ReceiptException("Выбрана некорректная дата.");
        }
    }

    public Receipt saveReceipt(RequestReceipt requestReceipt) throws ParseException {
        String[] splitData = requestReceipt.getDate().split(" ");
        if (checkDateAccuracy(splitData[0])) {
            throw new IllegalArgumentException("Выбрана некорректная дата.");
        }

        if (!checkTime(splitData[1])) {
            throw new IllegalArgumentException("Выбрано некорректное время.");
        }

        List<Receipt> employedSlots = getReceiptForDay(splitData[0]);
        for (Receipt employedSlot : employedSlots) {
            int slotHour = employedSlot.getStartOfReception().get(Calendar.HOUR_OF_DAY);
            String time = slotHour + ":00";
            if (time.equals(splitData[1])) {
                throw new IllegalArgumentException("Выбрано занятое время.");
            }
        }

        Receipt receipt = new Receipt();
        User user = userRepository.getById(requestReceipt.getUserId());
        receipt.setUser(user);
        Date desiredDate = new SimpleDateFormat("dd.MM.yyyy HH:mm").parse(requestReceipt.getDate());
        Calendar calStartOfReception = Calendar.getInstance();
        Calendar calCreationDate = Calendar.getInstance();
        receipt.setCreationDate(calCreationDate);
        calStartOfReception.setTime(desiredDate);
        receipt.setStartOfReception(calStartOfReception);
        receipt.setWasUsed(false);
        receipt.setLinkWaitingForConfirmation(UUID.randomUUID());
        return receiptRepository.save(receipt);
    }

    public Receipt approveReceipt(UUID uuid) {
        Receipt receipt = receiptRepository.findReceiptByLinkWaitingForConfirmation(uuid).orElseThrow(NoReceiptException::new);
        if (receipt.getConfirmation() != null && receipt.getConfirmation()) {
            throw new ReceiptIsAlreadyConfirmedException();
        }
        long creationSeconds = receipt.getCreationDate().toInstant().getEpochSecond();
        long nowSeconds = Calendar.getInstance().toInstant().getEpochSecond();
        if (nowSeconds - creationSeconds > receiptTimeout) {
            throw new ReceiptIsTimedOutException(receiptTimeout);
        }
        receipt.setConfirmation(true);
        return receiptRepository.save(receipt);
    }

    public Receipt getNearestReceipt() {
        removeObsoleteReceipts();
        return receiptRepository
                .findFirstByStartOfReceptionGreaterThanEqualAndWasUsedFalse(Calendar.getInstance())
                .orElseThrow(NoReceiptException::new);
    }

    public Receipt useReceipt(int receiptId) {
        Receipt receipt = receiptRepository.findById(receiptId).orElseThrow(NoReceiptException::new);
        receipt.setWasUsed(true);
        return receiptRepository.save(receipt);
    }

    public Receipt removeReceipt(int receiptId) {
        Receipt receipt = receiptRepository.findById(receiptId).orElseThrow(NoReceiptException::new);
        receiptRepository.delete(receipt);
        return receipt;
    }

    private void removeObsoleteReceipts() {
        Calendar dateBeforeTimeout = Calendar.getInstance();
        dateBeforeTimeout.add(Calendar.SECOND, -receiptTimeout);
        List<Receipt> timeoutReceipts = receiptRepository.findAllByCreationDateLessThanEqualAndConfirmationFalse(dateBeforeTimeout);
        receiptRepository.deleteAllInBatch(timeoutReceipts);
    }
}