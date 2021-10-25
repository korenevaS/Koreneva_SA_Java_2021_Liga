package com.github.korenevaS.queue.error;

public class ReceiptIsTimedOutException extends RuntimeException {
    public ReceiptIsTimedOutException(int minutes) {
        super("На подтверждение брони имеется лишь " + minutes + " минут");
    }
}
