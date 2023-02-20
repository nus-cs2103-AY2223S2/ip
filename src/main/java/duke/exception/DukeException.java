package duke.exception;

/**
 * Handles all exceptions.
 */
public class DukeException extends Exception {
    DukeException(String message) {
        super(message);
    }

    /**
     * Handles exception to missing time input for deadline and event.
     */
    public static void missingTimingException(String command) throws DukeException {
        if (command.startsWith("deadline") && !command.contains("/by")) {
            throw new DukeException("\t____________________________________________________________"
                    + "\n\t ☹ OOPS!!! The timing of a deadline cannot be empty."
                    + "\n\t____________________________________________________________");
        } else if (command.startsWith("event") && !command.contains("/from")) {
            throw new DukeException("\t____________________________________________________________"
                    + "\n\t ☹ OOPS!!! The start time of an event cannot be empty."
                    + "\n\t____________________________________________________________");
        } else if (command.startsWith("event") && !command.contains("/to")) {
            throw new DukeException("\t____________________________________________________________"
                    + "\n\t ☹ OOPS!!! The end time of an event cannot be empty."
                    + "\n\t____________________________________________________________");
        }
    }

    /**
     * Handles exception to missing index input of task when marking/
     * unmarking/ deleting task.
     */
    public static void missingIndexException(String command) throws DukeException {
        if (command.equals("mark")) {
            throw new DukeException("\t____________________________________________________________"
                    + "\n\t ☹ OOPS!!! The task index to mark a task as done cannot be empty."
                    + "\n\t____________________________________________________________");
        } else if (command.equals("unmark")) {
            throw new DukeException("\t____________________________________________________________"
                    + "\n\t ☹ OOPS!!! The task index to unmark a task as not done cannot be empty."
                    + "\n\t____________________________________________________________");
        } else if (command.equals("delete")) {
            throw new DukeException("\t____________________________________________________________"
                    + "\n\t ☹ OOPS!!! The task index to delete a task as not done cannot be empty."
                    + "\n\t____________________________________________________________");
        }
    }

    /**
     * Handles exception to invalid index input of task when marking/
     * unmarking/ deleting task. This occurs when
     */
    public static void invalidIndexException(String command, int taskSize) throws DukeException {
        if (command.startsWith("mark") || command.startsWith("unmark") ||command.startsWith("delete")) {
            String index = command.split(" ")[1];
            int index1 = Integer.parseInt(index);
            if (index1 <= 0) {
                throw new DukeException("\t____________________________________________________________"
                        + "\n\t ☹ OOPS!!! The task index to delete or un/mark a task cannot be zero or less."
                        + "\n\t____________________________________________________________");
            } else if (index.equals("")) {
                throw new DukeException("\t____________________________________________________________"
                        + "\n\t ☹ OOPS!!! The task index to delete or un/mark a task cannot be empty."
                        + "\n\t____________________________________________________________");
            } else if (index1 > taskSize) {
                throw new DukeException("\t____________________________________________________________"
                        + "\n\t ☹ OOPS!!! The task index to delete or un/mark a task cannot be more than"
                        + " number of tasks."
                        + "\n\t____________________________________________________________");
            }
        }
    }

    /**
     * Handles exception to missing command keyword.
     */
    public static void invalidCommandException(String command) throws DukeException {
        if (!command.startsWith("event") || !(command.startsWith("deadline")) ||
                !command.startsWith("todo") || command.startsWith("mark") || !command.startsWith("unmark")) {
            throw new DukeException("\t____________________________________________________________"
                    + "\n\t ☹ OOPS!!! I'm sorry, but I don't know what that means :-("
                    + "\n\t____________________________________________________________");
        }
    }

    /**
     * Handles exception to missing task description.
     */
    public static void emptyCommandException(String command) throws DukeException {
        if (command.equals("todo")) {
            throw new DukeException("\t____________________________________________________________"
                    + "\n\t ☹ OOPS!!! The description of a todo cannot be empty."
                    + "\n\t____________________________________________________________");
        } else if (command.equals("deadline")) {
            throw new DukeException("\t____________________________________________________________"
                    + "\n\t ☹ OOPS!!! The description of a deadline cannot be empty."
                    + "\n\t____________________________________________________________");
        } else if (command.equals("event")) {
            throw new DukeException("\t____________________________________________________________"
                    + "\n\t ☹ OOPS!!! The description of an event cannot be empty."
                    + "\n\t____________________________________________________________");
        } else if (command.equals("find deadlines or events on")) {
            throw new DukeException("\t____________________________________________________________"
                    + "\n\t ☹ OOPS!!! The date of a deadline/ event cannot be empty."
                    + "\n\t____________________________________________________________");
        }
    }
}
