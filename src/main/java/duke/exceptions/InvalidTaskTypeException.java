package duke.exceptions;

public class InvalidTaskTypeException extends Exception {

    public InvalidTaskTypeException() {
        super("OOPS!!! I'm sorry, but I don't know what that task type means :-("
         + "\n" + "task types are : todo, deadline, event");
    }
}
