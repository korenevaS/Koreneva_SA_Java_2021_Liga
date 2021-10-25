package com.github.korenevaS.queue.error;

public class NoReceiptException extends RuntimeException {
    public NoReceiptException() {
        super("No receipt found");
    }
}
