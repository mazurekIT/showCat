package it.mazurek.showcat.exceptions;

public class ToManyJudgesException extends RuntimeException {
    public ToManyJudgesException() {
        super("Already 3 judges voted");
    }
}