package ru.achugr.web.rest;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.achugr.service.FizzBuzzGameService;
import ru.achugr.web.rest.dto.QuestionAnswer;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Optional;
import java.util.Set;


/**
 * author: achugr
 * 16.02.16.
 */

@Api(value = "FizzBuzz game", description = "This API allows to build Fizz-Buzz game")
@RestController
@RequestMapping("/api/games/fizzBuzz")
public class FizzBuzzController {

    @Autowired
    FizzBuzzGameService fizzBuzzService;

    @ApiOperation(value = "This method allows to answer a Fizz-Buzz question in POST-way")
    @RequestMapping(method = RequestMethod.POST,
            value = "/getAnswer",
            produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public String getAnswerPOST(
            @ApiParam(value = "Question must be an urlencoded utf-8 string and contain only " +
                    "integer numbers separated by space character") @RequestParam Optional<String> question) {
        return answer(question);
    }

    @ApiOperation(value = "This method allows to answer a Fizz-Buzz question in GET-way")
    @RequestMapping(method = RequestMethod.GET,
            value = "/getAnswer",
            produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public String getAnswerGET(
            @ApiParam(value = "Question must be an urlencoded utf-8 string and contain only " +
                    "integer numbers separated by space character") @RequestParam("question") Optional<String> question) {
        return answer(question);
    }

    @ApiOperation(value = "Return one question-answer. If you want to play several rounds " +
            "use method /getQuestionAndAnswerList to increase velocity")
    @RequestMapping(method = RequestMethod.GET,
            value = "/getQuestionAndAnswer",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public QuestionAnswer getQuestionAndAnswer(
            @ApiParam(value = "Upper bound of generated questions") @RequestParam("bound") Optional<Integer> bound) {
        return fizzBuzzService.getRandomQuestionWithAnswer(bound);
    }

    @ApiOperation(value = "Return list of unique question-answer. If you want to play several rounds use this method")
    @RequestMapping(method = RequestMethod.GET,
            value = "/getQuestionAndAnswerList",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<QuestionAnswer> getQuestionAndAnswer(
            @ApiParam(value = "Upper bound of generated questions") @RequestParam("bound") Optional<Integer> bound,
            @ApiParam(value = "Size of question-answer collection. Important: size should be smaller than bound " +
                    "in other way you'll get only bound elements") @RequestParam("size") Optional<Integer> size) {
        return fizzBuzzService.getRandomQuestionWithAnswerList(bound, size);
    }

    private String answer(Optional<String> inputStr) {
        try {
            if (inputStr.isPresent()) {
                return fizzBuzzService.answer(URLDecoder.decode(inputStr.get(), "UTF-8"));
            } else {
                return "Input string must not be null";
            }
        } catch (UnsupportedEncodingException e) {
            return "Can't decode request parameter";
        }
    }
}

