package cn.taizhou0523.mail;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;

public abstract class Mail {

    Session session = null;
    private String receiver = null;
    private String sender = null;
    private String senderPassword = null;
    private Message message = null;

    public static Mail getInstance(String className) throws ClassNotFoundException {
        switch (className.toUpperCase()) {
            case "QQMAIL":
                return new QQMail();
            case "qqmail":
                return null;
            default:
                throw new ClassNotFoundException();
        }
    }

    {
        setSession();
    }

    abstract void setSession();

    public final Session getSession() {
        return session;
    }

    public final void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public final String getReceiver() {
        return receiver;
    }

    public final void setSender(String sender) {
        this.sender = sender;
    }

    public final String getSender() {
        return sender;
    }

    public final void setSenderPassword(String senderPassword) {
        this.senderPassword = senderPassword;
    }

    public final String getSenderPassword() {
        return senderPassword;
    }

    public final void setMessage(String subject, String text) throws MessagingException {
        message = new MimeMessage(session);
        message.setFrom(new InternetAddress(sender));
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiver));
        message.setSubject(subject);
        message.setText(text);
        message.setSentDate(new Date());
        message.saveChanges();
    }

    public final Message getMessage() {
        return message;
    }

    public final void send() throws MessagingException {
        Transport transport = session.getTransport();
        transport.connect(sender, senderPassword);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }
}
