package com.github.korenevaS.queue.repository;

import com.github.korenevaS.queue.repository.model.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ReceiptRepository extends JpaRepository<Receipt, Integer> {
    List<Receipt> findAllByStartOfReceptionLessThanEqualAndStartOfReceptionGreaterThanEqual(Calendar endDate, Calendar startDate);
    Optional<Receipt> findReceiptByLinkWaitingForConfirmation(UUID uuid);
    Optional<Receipt> findFirstByStartOfReceptionGreaterThanEqualAndWasUsedFalse(Calendar date);
    List<Receipt> findAllByCreationDateLessThanEqualAndConfirmationFalse(Calendar date);
}
