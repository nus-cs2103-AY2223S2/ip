package main.java;

public enum ERROR {
    TODO_EMPTY("☹ OOPS!!! The description of a todo cannot be empty."),
    DEADLINE_EMPTY("☹ OOPS!!! The description of a deadline cannot be empty."),
    EVENT_EMPTY("☹ OOPS!!! The description of an event cannot be empty."),
    INVALID_INPUT("☹ OOPS!!! I'm sorry, but I don't know what that means :-("),
    INVALID_INDEX("Invalid index!\nThere are only %d tasks in the list.");

    private final String message;

    ERROR(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
