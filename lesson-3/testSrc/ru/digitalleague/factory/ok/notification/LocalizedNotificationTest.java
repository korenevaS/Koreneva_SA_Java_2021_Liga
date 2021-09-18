package ru.digitalleague.factory.ok.notification;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.digitalleague.factory.ok.User;
import ru.digitalleague.factory.ok.enums.Language;
import ru.digitalleague.factory.ok.enums.NotificationPart;
import ru.digitalleague.factory.ok.notification.factory.MailNotificationFactory;
import ru.digitalleague.factory.ok.notification.factory.NotificationFactory;
import ru.digitalleague.factory.ok.notification.factory.PhoneNotificationFactory;

import java.util.Locale;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;

class LocalizedNotificationTest {
    @DisplayName("English mail notification with ads is correct")
    @Test
    void testEnglishMailNotification() {
        User userEn = new User(4L, "Dennis", "dennis@yahoo.com", "+92322668105", Language.ENGLISH);
        NotificationFactory factory = new MailNotificationFactory();
        assertEquals("Address: dennis@yahoo.com\n" +
                "Dear Dennis,\n" +
                "Good afternoon!\n" +
                "\n" +
                "Buy our products\n" +
                "\n" +
                "Regards, support team!", getLocalizedGreetingNotification(userEn, factory));
    }

    @DisplayName("English mail notification without ads is correct")
    @Test
    void testEnglishMailNotificationWithoutAds() {
        User userEn = new User(3L, "Dennis", "dennis@yahoo.com", "+92322668105", Language.ENGLISH);
        NotificationFactory factory = new MailNotificationFactory();
        assertEquals("Address: dennis@yahoo.com\n" +
                "Dear Dennis,\n" +
                "Good afternoon!\n" +
                "Regards, support team!", getLocalizedGreetingNotification(userEn, factory));
    }

    @DisplayName("Russian mail notification with ads is correct")
    @Test
    void testRussianMailNotification() {
        User userRu = new User(2L, "Денис", "denis@gmail.com", "+79522668105", Language.RUSSIAN);
        NotificationFactory factory = new MailNotificationFactory();
        assertEquals("Адрес: denis@gmail.com\n" +
                "Уважаемый Денис,\n" +
                "Добрый день!\n" +
                "\n" +
                "Покупайте наши товары\n" +
                "\n" +
                "С уважением, команда поддержки!", getLocalizedGreetingNotification(userRu, factory));
    }

    @DisplayName("Russian mail notification without ads is correct")
    @Test
    void testRussianMailNotificationWithoutAds() {
        User userRu = new User(3L, "Денис", "denis@gmail.com", "+79522668105", Language.RUSSIAN);
        NotificationFactory factory = new MailNotificationFactory();
        assertEquals("Адрес: denis@gmail.com\n" +
                "Уважаемый Денис,\n" +
                "Добрый день!\n" +
                "С уважением, команда поддержки!", getLocalizedGreetingNotification(userRu, factory));
    }

    @DisplayName("English phone notification is correct")
    @Test
    void testEnglishPhoneNotificationWithoutAds() {
        User userEn = new User(3L, "Dennis", "dennis@yahoo.com", "+92322668105", Language.ENGLISH);
        NotificationFactory factory = new PhoneNotificationFactory();
        assertEquals("Phone #+92322668105\n" +
                "Good afternoon!", getLocalizedGreetingNotification(userEn, factory));
    }

    @DisplayName("Russian phone notification is correct")
    @Test
    void testRussianPhoneNotificationWithoutAds() {
        User userRu = new User(2L, "Денис", "denis@gmail.com", "+79522668105", Language.RUSSIAN);
        NotificationFactory factory = new PhoneNotificationFactory();
        assertEquals("Телефон #+79522668105\n" +
                "Добрый день!", getLocalizedGreetingNotification(userRu, factory));
    }


    private static String getLocalizedGreetingNotification(User user, NotificationFactory factory) {
        ResourceBundle bundle = ResourceBundle.getBundle(
                "bundle",
                Locale.forLanguageTag(user.getLanguage().getLocaleCode())
        );
        Notification originalNotification = factory.makeNotification(NotificationPart.GREETING.getCode(), user);
        return new LocalizedNotification(originalNotification, bundle).getText();
    }


}