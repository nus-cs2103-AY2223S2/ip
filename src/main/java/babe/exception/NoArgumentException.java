package babe.exception;

/**
 * This exception is thrown when an instruction to add a Task is not followed by a description.
 */
public class NoArgumentException extends Exception {

    public NoArgumentException(String argType) {
        super(String.format("I need to know what is the %s of the task, bestie!", argType));
    }
}
