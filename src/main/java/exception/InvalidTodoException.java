package exception;
public class InvalidTodoException extends Exception {

    public InvalidTodoException() {
        super("The description of a todo cannot be empty.\n");
    }
}

