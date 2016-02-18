package ru.achugr.service;

import ru.achugr.web.rest.dto.QuestionAnswer;

import java.util.Optional;

/**
 * author: achugr
 * 16.02.16.
 */
public interface FizzBuzzGameService {
    String answer(String input);

    String answer(Integer input);

    QuestionAnswer getRandomQuestionWithAnswer(Optional<Integer> bound);
}
