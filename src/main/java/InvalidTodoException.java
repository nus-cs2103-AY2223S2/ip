public class InvalidTodoException extends Exception {

    InvalidTodoException() {
        super("The description of a todo cannot be empty.\n");
    }
}

