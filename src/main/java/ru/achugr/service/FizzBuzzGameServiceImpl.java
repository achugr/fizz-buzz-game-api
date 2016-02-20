package ru.achugr.service;

import org.springframework.stereotype.Service;
import ru.achugr.web.rest.dto.QuestionAnswer;

import java.util.*;
import java.util.stream.Collectors;

/**
 * author: achugr
 * 16.02.16.
 */
@Service
public class FizzBuzzGameServiceImpl implements FizzBuzzGameService {

    private static final Integer DEFAULT_BOUND = 100;
    private static final Integer DEFAULT_SET_SIZE = 3;

    @Override
    public String answer(String input) {
        if (input.trim().isEmpty()) {
            return "";
        }
        try {
            return Arrays.asList(input.trim().replaceAll(" +", " ").split("\\s"))
                    .stream()
                    .map(Integer::parseInt)
                    .map(this::answer)
                    .collect(Collectors.joining(" "));
        } catch (NumberFormatException e) {
            return "Input string must contain only integer numbers and space chars";
        }
    }

    @Override
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

    @Override
    public List<QuestionAnswer> getRandomQuestionWithAnswerList(Optional<Integer> boundOpt, Optional<Integer> sizeOpt) {
        Integer bound = boundOpt.orElse(DEFAULT_BOUND);
        Integer setSize = sizeOpt.orElse(DEFAULT_SET_SIZE);
//        if setSize lower than bound, we can't generate set of unique values with specified size
//        so we'll return set with size == bounds
        setSize = setSize < bound ? setSize : bound;
        Set<Integer> questions = new HashSet<>(setSize);
//  TODO implement algorithm for generating random set if ! (size << bound)
//        basic idea: to build an array of integers from lowerBound (0 at the beginning) to upperBound (@bound),
//        to get element at random index (from lowerBound to upperBound) and put it into the questions,
//        then swap this element with element at lowerBound and increment lowerBound.
//        So we'll get unique elements and this method guaranties,
//        that we build @size values with O(n) complexity
        while (questions.size() < setSize) {
            questions.add(new Random().nextInt(bound));
        }
        return questions.stream()
                .map(question -> new QuestionAnswer(question, answer(question)))
                .collect(Collectors.toList());

    }

}
