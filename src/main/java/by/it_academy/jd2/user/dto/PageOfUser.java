package by.it_academy.jd2.user.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PageOfUser {
    int number;
    int size;
    int total_pages;
    long total_elements;
    boolean first;
    int number_of_elements;
    boolean last;
    List<User> content;

}
