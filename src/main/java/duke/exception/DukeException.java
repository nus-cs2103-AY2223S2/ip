package main.java.duke.exception;

public class DukeException extends RuntimeException {

    public DukeException(String error, Throwable err) {
        super(error, err);
    }

}
