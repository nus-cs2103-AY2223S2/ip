package duke;

class DukeException extends Exception {
    /**
     * General Duke exception. Used for testing.
     *
     * @param message Print out any message entered
     */
    public DukeException(String message) {
        super(message);
    }
}

class markException extends DukeException {
    /**
     * Exception used by marking function to indicate that user input is invalid
     */

    public markException() {
        super("I don't know which task to mark...");
    }
}


class taskNotFoundException extends DukeException {
    /**
     * Exception used to indicate user input was invalid and/or task was not found in TaskList
     */

    public taskNotFoundException() {
        super("Task not found...");
    }
}

class deleteException extends DukeException {
    /**
     * Exception used by delete function to indicate invalid user input
     */

    public deleteException() {
        super("I don't know which task to delete...");
    }
}

class todoException extends DukeException {
    /**
     * Exception used by todo task to indicate invalid user input when creating todo task
     */

    public todoException() {
        super("☹ OOPS!!! The description of a todo cannot be empty.");
    }
}


class deadlineException extends DukeException {
    /**
     * Exception used by deadline task to indicate invalid user input when creating deadline task
     */

    public deadlineException() {
        super("☹ OOPS!!! The description of a deadline cannot be empty.");
    }
}


class eventException extends DukeException {
    /**
     * Exception used by event task to indicate invalid user input when creating event task
     */

    public eventException() {
        super("☹ OOPS!!! The description of a event cannot be empty.");
    }
}


