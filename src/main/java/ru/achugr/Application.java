package ru.achugr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

/**
 * author: achugr
 * 16.02.16.
 */
@SpringBootApplication
@ComponentScan(includeFilters =
        {
                @ComponentScan.Filter(pattern = "ru.achugr.service.*", type = FilterType.REGEX),
        }
)
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
