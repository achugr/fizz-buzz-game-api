package ru.achugr.service;

import org.springframework.stereotype.Service;
import ru.achugr.web.rest.dto.QuestionAnswer;

import java.util.Arrays;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * author: achugr
 * 16.02.16.
 */
@Service
public class FizzBuzzGameServiceImpl implements FizzBuzzGameService {

    private static final Integer DEFAULT_BOUND = 100;

    @Override
    public String answer(String input) {
        if (input.trim().isEmpty()) {
            return "";
        }
        return Arrays.asList(input.trim().replaceAll(" +", " ").split("\\s"))
                .stream()
                .map(Integer::parseInt)
                .map(this::answer)
                .collect(Collectors.joining(" "));
    }

    public String answer(Integer num) {
        String result = (num % 3 == 0 ? "Fizz" : "") + (num % 5 == 0 ? "Buzz" : "");
        return result.isEmpty() ? num.toString() : result;
    }

    @Override
    public QuestionAnswer getRandomQuestionWithAnswer(Optional<Integer> boundOpt) {
        Integer num = new Random().nextInt(boundOpt.orElse(DEFAULT_BOUND));
        return QuestionAnswer.builder()
                .question(num)
                .answer(answer(num))
                .build();
    }

}
