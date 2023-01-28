public class DukeException extends Exception {

    private static final String MSG_ERR_PREFIX = "☹ OOPS!!!";
    private static final String MSG_ERR_SUFFIX = ":-(";

    /** List of known error messages */
    private static final String MSG_ERR_DEFAULT = "I'm sorry, something went wrong.";

    // Error messages for list issues
    private static final String MSG_ERR_LIST_ECHO = "It appears that the message and indentation list are unbalanced.";
    private static final String MSG_ERR_LIST_EMPTY = "The list of task is empty.";

    // Error messages for event task issues
    private static final String MSG_ERR_EVENT_EMPTY = "The description of an event cannot be empty.";
    private static final String MSG_ERR_EVENT_FROM = "The start time of an event cannot be empty.";
    private static final String MSG_ERR_EVENT_TO = "The end time of an event cannot be empty.";

    // Error messages for deadline task issues
    private static final String MSG_ERR_DEADLINE_DESC = "The description of a deadline cannot be empty.";
    private static final String MSG_ERR_DEADLINE_TIME = "The deadline of a deadline cannot be empty.";

    // Error messages for issues with deleting task from the list of tasks
    private static final String MSG_ERR_DELETE_EMPTY = "The index of the task to be deleted cannot be empty.";
    private static final String MSG_ERR_DELETE_BOUND = "The index of the task to be deleted must be in the list.";
    private static final String MSG_ERR_DELETE_NONINT = "The index of the task to be deleted must be an integer.";

    // Error messages for issues with marking task as complete
    private static final String MSG_ERR_MARK_EMPTY = "The index of the task to be marked as done cannot be empty.";
    private static final String MSG_ERR_MARK_BOUND = "The index of the task to be marked as done must be in the list.";
    private static final String MSG_ERR_MARK_NONINT = "The index of the task to be marked as done must be an integer.";

    private static final String MSG_ERR_TODO_DESC = "The description of a todo cannot be empty.";
    private static final String MSG_ERR_UNKNOWN = "I'm sorry, but I don't know what that means.";

    // Error messages for issues with changing the status of task to not complete
    private static final String MSG_ERR_UNMARK_EMPTY = "The index of the task to be marked as not done cannot be empty.";
    private static final String MSG_ERR_UNMARK_BOUND = "The index of the task to be marked as not done must be in the list.";
    private static final String MSG_ERR_UNMARK_NONINT = "The index of the task to be marked as not done must be an integer.";

    public DukeException(String errorMessage) {
        super(MSG_ERR_PREFIX
                + " " + errorMessage
                + " " + MSG_ERR_SUFFIX);
    }

    public DukeException(String type, String errorMessage) throws DukeException {
        super(errorMessage);
        switch (type) {
        case "Unknown":
            if (errorMessage.equals("Unknown Command")) {
                throw new DukeException(MSG_ERR_UNKNOWN);
            }
            break;
        case "ToDo":
            if (errorMessage.equals("Empty description")) {
                throw new DukeException(MSG_ERR_TODO_DESC);
            }
            break;
        case "Deadline":
            switch (errorMessage) {
                case "Empty description":
                    throw new DukeException(MSG_ERR_DEADLINE_DESC);
                case "Empty deadline":
                    throw new DukeException(MSG_ERR_DEADLINE_TIME);
            }
            break;
        case "Event":
            switch (errorMessage) {
                case "Empty description":
                    throw new DukeException(MSG_ERR_EVENT_EMPTY);
                case "Empty From Time":
                    throw new DukeException(MSG_ERR_EVENT_FROM);
                case "Empty To Time":
                    throw new DukeException(MSG_ERR_EVENT_TO);
            }
            break;
        case "Mark":
            switch (errorMessage) {
                case "Empty Index":
                    throw new DukeException(MSG_ERR_MARK_EMPTY);
                case "Out of Bound":
                    throw new DukeException(MSG_ERR_MARK_BOUND);
                case "Not Integer":
                    throw new DukeException(MSG_ERR_MARK_NONINT);
            }
            break;
        case "Unmark":
            switch (errorMessage) {
                case "Empty Index":
                    throw new DukeException(MSG_ERR_UNMARK_EMPTY);
                case "Out of Bound":
                    throw new DukeException(MSG_ERR_UNMARK_BOUND);
                case "Not Integer":
                    throw new DukeException(MSG_ERR_UNMARK_NONINT);
            }
            break;
        case "Delete":
            switch (errorMessage) {
                case "Empty Index":
                    throw new DukeException(MSG_ERR_DELETE_EMPTY);
                case "Out of Bound":
                    throw new DukeException(MSG_ERR_DELETE_BOUND);
                case "Not Integer":
                    throw new DukeException(MSG_ERR_DELETE_NONINT);
            }
            break;
        case "List":
            switch (errorMessage) {
                case "Empty List":
                    throw new DukeException(MSG_ERR_LIST_EMPTY);
                case "Unbalanced List":
                    throw new DukeException(MSG_ERR_LIST_ECHO);
            }
            break;
        }
    }

    public static String[] unimplemented(Exception e) {
        return new String[]{MSG_ERR_DEFAULT,
                "Error Message ("
                        + e.getClass().getName()
                        + "):",
                e.getMessage()};
    }
}
