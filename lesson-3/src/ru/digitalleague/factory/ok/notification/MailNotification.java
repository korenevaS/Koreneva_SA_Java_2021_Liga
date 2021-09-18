package ru.digitalleague.factory.ok.notification;


import ru.digitalleague.factory.ok.User;
import ru.digitalleague.factory.ok.enums.NotificationPart;

public class MailNotification implements Notification {

    private final String body;
    private final User user;
    private final boolean hasAdvertisement;

    public MailNotification(String body, User user, boolean hasAdvertisement) {
        this.body = body;
        this.user = user;
        this.hasAdvertisement = hasAdvertisement;
    }

    public String getText() {
        return String.format(
                NotificationPart.ADDRESS.getCode() + " %s\n" + NotificationPart.HEADER.getCode() +
                        " %s,\n%s%s\n" + NotificationPart.FOOTER.getCode(),
                user.getEmail(),
                user.getName(),
                body,
                hasAdvertisement ? "\n\n" + NotificationPart.AD.getCode() + "\n" : ""
        );
    }
}
