package ru.achugr.web.rest;

import com.wordnik.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.achugr.service.FizzBuzzGameService;
import ru.achugr.web.rest.dto.QuestionAnswer;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Optional;


/**
 * author: achugr
 * 16.02.16.
 */

@Api(value = "FizzBuzz game", description = "This API allows to build Fizz-Buzz game")
@RestController
@RequestMapping("/games/fizzBuzz")
public class FezzBuzzController {

    @Autowired
    FizzBuzzGameService fizzBuzzService;

    @RequestMapping(method = RequestMethod.POST,
            value = "/getAnswer",
            produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public String getAnswerPOST(@RequestParam String question) {
        return play(question);
    }

    @RequestMapping(method = RequestMethod.GET,
            value = "/getAnswer",
            produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public String getAnswerGET(@RequestParam("num") String question) {
        return play(question);
    }

    @RequestMapping(method = RequestMethod.GET,
            value = "/getQuestionAndAnswer",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public QuestionAnswer getQuestionAndAnswer(@RequestParam("bound") Optional<Integer> bound) {
        return fizzBuzzService.getRandomQuestionWithAnswer(bound);
    }

    private String play(String inputStr) {
        try {
            return fizzBuzzService.answer(URLDecoder.decode(inputStr, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            return "Can't decode request parameter";
        }
    }
}

