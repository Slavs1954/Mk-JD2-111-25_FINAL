package by.it_academy.jd2.mail.storage.api;

import by.it_academy.jd2.mail.storage.entity.MailEntity;
import org.springframework.data.util.Pair;

import java.util.List;
import java.util.Optional;

public interface IMailStorage {
    boolean add(String mail, String code);
    boolean verify(String mail);
    String getCode(String mail);
    boolean isVerified(String mail);

    /**
     * Retrieves a list of unverified email addresses along with their associated verification codes.
     * <p>
     * Each entry in the list is represented as a {@link Pair}:
     * <ul>
     *   <li>{@code first} – the email address that has not yet been verified</li>
     *   <li>{@code second} – the verification code linked to that email address</li>
     * </ul>
     *
     * @return a list of pairs containing unverified email addresses and their corresponding codes;
     *         never {@code null}, but may be empty if all emails are verified
     */
    List<Pair<String, String>> getUnverifiedMailsAndCodes();
    void incrementVerifiedMailCount(String mail);
}
