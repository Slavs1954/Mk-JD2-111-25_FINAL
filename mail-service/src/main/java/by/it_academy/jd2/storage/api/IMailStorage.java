package by.it_academy.jd2.storage.api;

import org.springframework.data.util.Pair;

import java.util.List;
import java.util.UUID;

public interface IMailStorage {
    boolean add(UUID userId, String code);
    boolean verify(UUID userId);
    String getCode(UUID userId);
    boolean isVerified(UUID userId);

    /**
     * Retrieves a list of unverified userId's along with their associated verification codes.
     * <p>
     * Each entry in the list is represented as a {@link Pair}:
     * <ul>
     *   <li>{@code first} – the userId that has not yet been verified</li>
     *   <li>{@code second} – the verification code linked to that userId</li>
     * </ul>
     *
     * @return a list of pairs containing unverified userId's and their corresponding codes;
     *         never {@code null}, but may be empty if all emails are verified
     */
    List<Pair<UUID, String>> getUnverifiedUserIdAndCodes();
    void incrementVerifiedMailCount(UUID userId);
}
