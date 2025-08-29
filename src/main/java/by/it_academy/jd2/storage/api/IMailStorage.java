package by.it_academy.jd2.storage.api;

import org.springframework.data.util.Pair;

import java.util.List;

public interface IMailStorage {
    boolean add(String mail, String code);
    boolean verify(String mail);
    String getCode(String mail);

    //TODO: add documentation 1st pos mail, 2nd pos code
    List<Pair<String, String>> getUnverifiedMailsAndCodes();
    void incrementVerifiedMailCount(String mail);
}
