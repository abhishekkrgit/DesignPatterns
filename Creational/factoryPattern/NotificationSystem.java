package factoryPattern;

import java.util.HashMap;
import java.util.Map;

// create product

interface Notification {
    void send(String message);
}

class EmailNotificaton implements Notification {
    @Override
    public void send(String message) {
        System.out.println("Sending Email Notification with msg : "+ message);

    }
}

class PushNotificaton implements Notification {
    @Override
    public void send(String message) {
        System.out.println("Sending Push Notification with msg : "+ message);

    }
}

class SmsNotificaton implements Notification {
    @Override
    public void send(String message) {
        System.out.println("Sending SMS Notification with msg : "+ message);

    }
}

// create abstract class
interface NotificationCreator {
    public abstract Notification create();
}

class EmailNotificationCreator implements NotificationCreator {

    @Override
    public Notification create(){
        return new EmailNotificaton();
    }
}

class PushNotificationCreator implements NotificationCreator {

    @Override
    public Notification create(){
        return new PushNotificaton();
    }
}

class SmsNotificationCreator implements NotificationCreator {

    @Override
    public Notification create(){
        return new SmsNotificaton();
    }
}


public class NotificationSystem {
    private static final Map<String, NotificationCreator> registry = new HashMap<>();

    static {
        registry.put("EMAIL", new EmailNotificationCreator());
        registry.put("SMS", new SmsNotificationCreator());
        registry.put("PUSH", new PushNotificationCreator());
    }

    public static void main(String[] args) {
        // Business Logic: The client doesn't care which Creator it's using
        sendNotification("EMAIL", "Welcome to Amazon Pay!");
        sendNotification("SMS", "Your transaction was successful.");
    }

    private static void sendNotification(String type, String message) {
        NotificationCreator creator = registry.get(type);
        if (creator != null) {
            Notification note = creator.create();
            note.send(message);
        }
    }
}
