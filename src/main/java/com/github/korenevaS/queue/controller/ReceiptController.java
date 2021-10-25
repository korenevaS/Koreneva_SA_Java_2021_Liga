package com.github.korenevaS.queue.controller;

import com.github.korenevaS.queue.controller.model.*;
import com.github.korenevaS.queue.error.ReceiptException;
import com.github.korenevaS.queue.repository.model.Receipt;
import com.github.korenevaS.queue.repository.model.Role;
import com.github.korenevaS.queue.service.ReceiptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.annotation.security.RolesAllowed;
import java.net.URI;
import java.text.ParseException;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ReceiptController {
    public final ReceiptService receiptService;

    @PostMapping("/get-free-slots")
    public ResponseEntity<ResponseFreeSlots> getFreeSlots(
            @RequestBody RequestFreeSlots request
    ) {
        try {
            List<String> freeSlots = receiptService.listOfFreeSlots(request.getSelectedDate());
            String message;
            if (freeSlots.isEmpty()) {
                message = "Свободного времени для приема в этот день нет. Пожалуйста, выберете другой день.";
            } else {
                message = "Свободное время для приема: " + freeSlots;
            }
            return ResponseEntity.ok(new ResponseFreeSlots(message, freeSlots));
        } catch (ReceiptException e) {
            return ResponseEntity.badRequest().body(new ResponseFreeSlots(e.getMessage(), Collections.emptyList()));
        }
    }

    @PostMapping("/take-slot")
    public ResponseEntity<Receipt> takeSlot(
            @RequestBody RequestReceipt requestReceipt
    ) throws ParseException {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/take-slot").toUriString());
        Receipt receipt = receiptService.saveReceipt(requestReceipt);
        URI approveUri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path(
                "api/approve/" + receipt.getLinkWaitingForConfirmation()).toUriString()
        );
        log.info("Перейди по ссылке " + approveUri);
        return ResponseEntity.created(uri).body(receipt);
    }

    @GetMapping("/approve/{uuid}")
    public Receipt approveSlot(@PathVariable UUID uuid) {
        return receiptService.approveReceipt(uuid);
    }


    @GetMapping("/nearest-receipt")
    public Receipt getNearestReceipt() {
        return receiptService.getNearestReceipt();
    }


    @PostMapping("/use-receipt")
    public Receipt useReceipt(@RequestBody RequestUseReceipt request) {
        return receiptService.useReceipt(request.getReceiptId());
    }

    @PostMapping("/remove-receipt")
    public Receipt removeReceipt(@RequestBody RequestRemoveReceipt request) {
        return receiptService.removeReceipt(request.getReceiptId());
    }
}
