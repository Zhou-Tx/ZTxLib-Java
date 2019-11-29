package cn.taizhou0523.mail;

import javax.mail.Session;
import java.util.Properties;

class QQMail extends Mail {

    @Override
    void setSession() {
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.smtp.host", "smtp.qq.com");
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        session = Session.getDefaultInstance(props);
    }
}
