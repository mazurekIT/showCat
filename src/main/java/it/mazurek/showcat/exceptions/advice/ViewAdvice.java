package it.mazurek.showcat.exceptions.advice;

import it.mazurek.showcat.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ViewAdvice {
    @ResponseBody
    @ExceptionHandler({
            TicketAlreadyVotedException.class,
            CatNotFoundException.class,
            PointsNotValidException.class,
            ToManyJudgesException.class,
            JudgeAlreadyVotedException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String exceptionsHandler(Exception ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(CatNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String catNotFoundHandler(Exception ex) {
        return ex.getMessage();
    }
}
