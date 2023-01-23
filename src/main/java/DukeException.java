class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}

class EmptyDescriptionException extends DukeException {
    public EmptyDescriptionException() {
        super("☹ OOPS!!! The description of your task cannot be empty.");
    }
}

class InvalidInputException extends DukeException {
    public InvalidInputException() {
        super("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}