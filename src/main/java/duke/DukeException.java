package duke;

/**
 * Exception for handling possible Duke problems.
 */
public class DukeException extends Exception {

    /**
     * Class constructor.
     *
     * @param message the message to be displayed
     */
    public DukeException(String message) {
        super(message);
    }

    /*
     * Exception for handling wrong user input.
     *
    public void WrongCommandException() {
        if (this.task.equals("wrong")) {
            System.out.println("OOPS!!! I'm sorry, but I don't know what that means.");
        }
    }

    /**
     * Exception for handling user input with empty task description.
     *
    public void EmptyDescriptionException() {
        switch (this.task) {
        case "todo":
            System.out.println("OOPS!!! The description of a todo task cannot be empty.");
            break;
        case "deadline":
            System.out.println("OOPS!!! The description of a deadline task cannot be empty.");
            break;
        case "event":
            System.out.println("OOPS!!! The description of an event task cannot be empty.");
            break;
        }
    }

    /**
     * Exception for handling already marked/unmarked tasks.
     *
    public void MarkedException() {
        switch (this.task) {
        case "marked":
            System.out.println("This task has already been marked as done.");
            break;
        case "unmarked":
            System.out.println("This task was already marked as not done.");
            break;
        }
    }

    /**
     * Exception for handling user input with empty task duration.
     *
    public void EmptyTimeException() {
        if (this.task.equals("empty time")) {
            System.out.println("Please specify the time the time period for this task.");
        }
    }*/
}
