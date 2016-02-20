package ru.achugr.service;

import org.hamcrest.Matcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.Parameterized;
import ru.achugr.web.rest.dto.QuestionAnswer;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * author: achugr
 * 16.02.16.
 */
@RunWith(JUnit4.class)
public class FizzBuzzGameServiceImplQuestionAnswerTest {

    private FizzBuzzGameService fizzBuzzGameService = new FizzBuzzGameServiceImpl();

//    It's undetermenistic test, so it's not good test
    @Test
    public void testAnswer() throws Exception {
        Optional<Integer> bound = Optional.of(20);
        Optional<Integer> size = Optional.of(10);
        List<QuestionAnswer> questionAnswerList = fizzBuzzGameService.getRandomQuestionWithAnswerList(bound, size);
        questionAnswerList.forEach(questionAnswer -> {
            assertTrue(questionAnswer.getQuestion() < bound.get());
            assertThat(questionAnswer.getAnswer(), is(equalTo(fizzBuzzGameService.answer(questionAnswer.getQuestion()))));
        });
    }
}