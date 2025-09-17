package by.it_academy.jd2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class ClassifierServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClassifierServiceApplication.class, args);
    }
}
