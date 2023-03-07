package it.mazurek.showcat.exceptions;

public class JudgeAlreadyVotedException extends RuntimeException {
    public JudgeAlreadyVotedException() {
        super("Judge already voted");
    }
}