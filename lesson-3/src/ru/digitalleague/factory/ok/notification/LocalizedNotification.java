package ru.digitalleague.factory.ok.notification;

import ru.digitalleague.factory.ok.enums.NotificationPart;

import java.util.ResourceBundle;

public class LocalizedNotification implements Notification {
    private final Notification notification;
    private final ResourceBundle bundle;

    public LocalizedNotification(Notification notification, ResourceBundle bundle) {
        this.notification = notification;
        this.bundle = bundle;
    }

    @Override
    public String getText() {
        String message = notification.getText();
        for (NotificationPart part : NotificationPart.values()) {
            message = message.replace(part.getCode(), bundle.getString(part.name()));
        }
        return message;
    }
}
