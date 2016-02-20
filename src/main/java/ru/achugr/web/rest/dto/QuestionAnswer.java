package ru.achugr.web.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * author: achugr
 * 18.02.16.
 */
@Data
@Builder
@AllArgsConstructor
public class QuestionAnswer {
    private Integer question;
    private String answer;
}
