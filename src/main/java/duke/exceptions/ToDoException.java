package duke.exceptions;

public class ToDoException extends Exception {
    protected String message;

    public ToDoException(String str) {
        message = str;
    }

    public String toString() {
        return message;
    }
}
