package by.it_academy.jd2.dto;

import java.util.List;

public class PageOfUser {
    int number;
    int size;
    int total_pages;
    int total_elements;
    boolean first;
    int number_of_elements;
    boolean last;
    List<User> content;

}
