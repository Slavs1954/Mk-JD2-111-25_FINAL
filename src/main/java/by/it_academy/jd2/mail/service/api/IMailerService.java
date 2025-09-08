package by.it_academy.jd2.mail.service.api;

public interface IMailerService {
    boolean add(String mail, String code);
    boolean verify(String mail, String code);
    void sendMail(String mail, String code);
    boolean isVerified(String mail);
}
