package duke.exceptions;

public class ToDoException extends Exception{
    String message;
    public ToDoException(String str) {
        message = str;
    }
    public String toString() {
        return message;
    }
}
