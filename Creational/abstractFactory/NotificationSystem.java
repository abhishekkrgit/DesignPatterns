package abstractFactory;

import java.util.Objects;

// Products;
abstract class Message {
    protected String to;
    protected String body;

    public Message(String to, String body) {
        this.to = to;
        this.body = body;
    }

    abstract String format();
}

interface Sender {
    void send(Message message);
}


class EmailMessage extends Message{

    public EmailMessage(String to, String body){
        super(to, body);
    }

    @Override
    public String format(){
        StringBuilder formattedMsg = new StringBuilder("Email to");
        formattedMsg.append(" < ").append(to).append(" >: ").append(body);
        return formattedMsg.toString();
    }
}

class SmsMessage extends Message {

    public SmsMessage(String to, String body) {
        super(to, body);
    }
    
    @Override
    public String format(){
        StringBuilder formattedMsg = new StringBuilder("SMS to");
        formattedMsg.append("[ ").append(to).append(" ]: ").append(body);
        return formattedMsg.toString();
    }
}

class EmailSender implements Sender {

    @Override
    public void send(Message message){
        System.out.println("Sending via carriar SMTP: "+ message.format());
    }
}

class SmsSender implements Sender {

    @Override
    public void send(Message message) {
        System.out.println("Sending via api:" + message.format());
    }
}

// Factories

interface NotificationFactory {
    Message createMessage(String to, String body);
    Sender  createSender();
}

class EmailFactory implements NotificationFactory{

    @Override
    public Message createMessage(String to, String body){
        return new EmailMessage(to, body);
    }

    @Override
    public Sender createSender(){
        return new EmailSender();
    }
}

class SmsFactory implements NotificationFactory{

    @Override
    public Message createMessage(String to, String body){
        return new SmsMessage(to, body);
    }

    @Override
    public Sender createSender(){
        return new SmsSender();
    }
}



class NotificationOrchestrator {
    private final NotificationFactory notificationFactory;

    public NotificationOrchestrator(NotificationFactory notificationFactory){
        this.notificationFactory = notificationFactory;
    }

    public void notify(String to, String body) {
        if(Objects.isNull(to) || Objects.isNull(body)){
            System.out.println("incomplete notification");
            return;
        }
        Message message = notificationFactory.createMessage(to, body);
        Sender sender = notificationFactory.createSender();
        sender.send(message);
    }
}

public class NotificationSystem {
    public static void main(String[] args) {
        NotificationOrchestrator emailOrchestrator = new NotificationOrchestrator(new EmailFactory());
        NotificationOrchestrator smsOrchestrator = new NotificationOrchestrator(new SmsFactory());

        System.out.println(" ------ EMAIL NOTIFICATION -------");
        emailOrchestrator.notify("kraabhi@gmail.com", "Hi Abhishek, How are you?");

        System.out.println(" ------ SMS NOTIFICATION -------");
        smsOrchestrator.notify("+7408085232", "Your parcel has arrived.");

    }
}