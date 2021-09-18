package ru.digitalleague.factory.ok.enums;

public enum NotificationPart {
    GREETING,
    ADDRESS,
    HEADER,
    FOOTER,
    AD,
    PHONE;

    public String getCode() {
        return "{" + name() + "}";
    }
}
