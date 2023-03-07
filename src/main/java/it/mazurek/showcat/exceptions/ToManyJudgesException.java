package it.mazurek.showcat.exceptions;

public class ToManyJudgesException extends RuntimeException {
    public ToManyJudgesException() {
        super("Points should be between 0 and 10");
    }
}