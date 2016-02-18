package ru.achugr.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * author: achugr
 * 16.02.16.
 */
@RunWith(Parameterized.class)
public class FizzBuzzGameServiceImplTest {

    private FizzBuzzGameService fizzBuzzGameService = new FizzBuzzGameServiceImpl();

    private String inputStr;
    private String outputStr;

    public FizzBuzzGameServiceImplTest(String inputStr, String outputStr){
        this.inputStr = inputStr;
        this.outputStr = outputStr;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"", ""},
                {"1", "1"},
                {"3", "Fizz"},
                {"5", "Buzz"},
                {"15", "FizzBuzz"},
                {"3 5", "Fizz Buzz"},
                {"3 7 5", "Fizz 7 Buzz"},
                {"3 15 5", "Fizz FizzBuzz Buzz"},
                {"3 30 7", "Fizz FizzBuzz 7"},
                {"1 7 11 13", "1 7 11 13"},
        });
    }

    @Test
    public void testPlay() throws Exception {
        assertThat(fizzBuzzGameService.play(inputStr), is(equalTo(outputStr)));
    }
}