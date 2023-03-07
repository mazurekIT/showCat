package it.mazurek.showcat.exceptions;

public class PointsNotValidException extends RuntimeException {
    public PointsNotValidException() {
        super("Points should be between 0 and 10");
    }
}