package ru.digitalleague.factory.ok;

import ru.digitalleague.factory.ok.enums.Language;
import ru.digitalleague.factory.ok.enums.NotificationPart;
import ru.digitalleague.factory.ok.notification.LocalizedNotification;
import ru.digitalleague.factory.ok.notification.Notification;
import ru.digitalleague.factory.ok.notification.factory.NotificationFactory;
import ru.digitalleague.factory.ok.notification.factory.MailNotificationFactory;
import ru.digitalleague.factory.ok.notification.factory.PhoneNotificationFactory;

import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;

public class Main {

    public static void main(String[] args) {
        User userRu = new User(2L, "Денис", "denis@gmail.com", "+79522668105", Language.RUSSIAN);
        User userEn = new User(3L, "Dennis", "dennis@yahoo.com", "+92322668105", Language.ENGLISH);

        NotificationFactory factory = new Random().nextBoolean() ? new MailNotificationFactory() : new PhoneNotificationFactory();

        sendNotificationToUser(userRu, factory);
        sendNotificationToUser(userEn, factory);
    }

    private static void sendNotificationToUser(User user, NotificationFactory factory) {
        ResourceBundle bundle = ResourceBundle.getBundle(
                "bundle",
                Locale.forLanguageTag(user.getLanguage().getLocaleCode())
        );
        Notification originalNotification = factory.makeNotification(NotificationPart.GREETING.getCode(), user);
        LocalizedNotification notification = new LocalizedNotification(originalNotification, bundle);
        sendNotification(notification);
    }

    private static void sendNotification(Notification notification) {
        System.out.println(notification.getText());
    }
}
