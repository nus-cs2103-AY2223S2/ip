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

    private static final String MSG_ERR_NONE = "There seems to be no input.";

    // Error Messages for file handling issues
    private static final String MSG_ERR_FILE_CREATE = "There was an I/O error when trying to create the necessary directory and files to store list of tasks.";
    private static final String MSG_ERR_FILE_FORMAT = "The save file was corrupted.";
    private static final String MSG_ERR_FILE_LOAD = "There was an I/O error when trying to load the saved list of tasks.";
    private static final String MSG_ERR_FILE_SAVE = "There was an I/O error when trying to save the list of tasks.";

    // Error Messages for datetime issues
    private static final String MSG_ERR_TIME_PARSE = "The time cannot cannot be parsed.";
    private static final String MSG_ERR_TIME_DEADLINE = "The deadline has already been reached.";
    private static final String MSG_ERR_TIME_EVENT_OVER = "The event is already over.";
    private static final String MSG_ERR_TIME_EVENT_INVALID = "The end time of the event is before the start time of the event.";

    private static final String MSG_ERR_TODO_DESC = "The description of a todo cannot be empty.";
    private static final String MSG_ERR_UNKNOWN = "I'm sorry, but I don't know what that means.";

    // Error messages for issues with changing the status of task to not complete
    private static final String MSG_ERR_UNMARK_EMPTY = "The index of the task to be marked as not done cannot be empty.";
    private static final String MSG_ERR_UNMARK_BOUND = "The index of the task to be marked as not done must be in the list.";
    private static final String MSG_ERR_UNMARK_NONINT = "The index of the task to be marked as not done must be an integer.";

    protected enum ErrorType {UNKNOWN, TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE, LIST, FILE, TIME};


    /**
     * Holds the error message of the exception
     *
     * @param errorMessage Error message of the exception
     */
    public DukeException(String errorMessage) {
        super(MSG_ERR_PREFIX
                + " " + errorMessage
                + " " + MSG_ERR_SUFFIX);
    }

    /**
     * Handles the different type of errors in Duke
     *
     * @param type Type of method that caused the exception
     * @param errorMessage Type of exception
     * @throws DukeException For each type of errors
     */
    public DukeException(ErrorType type, String errorMessage) throws DukeException {
        super(type.toString() + " " + errorMessage);
        try {
            switch (type) {
            case UNKNOWN:
                switch (errorMessage) {
                case "Unknown Command":
                    throw new DukeException(MSG_ERR_UNKNOWN);
                case "No Command":
                    throw new DukeException(MSG_ERR_NONE);
                }
                break;
            case TODO:
                if (errorMessage.equals("Empty description")) {
                    throw new DukeException(MSG_ERR_TODO_DESC);
                }
                break;
            case DEADLINE:
                switch (errorMessage) {
                case "Empty description":
                    throw new DukeException(MSG_ERR_DEADLINE_DESC);
                case "Empty deadline":
                    throw new DukeException(MSG_ERR_DEADLINE_TIME);
                }
                break;
            case EVENT:
                switch (errorMessage) {
                case "Empty description":
                    throw new DukeException(MSG_ERR_EVENT_EMPTY);
                case "Empty From Time":
                    throw new DukeException(MSG_ERR_EVENT_FROM);
                case "Empty To Time":
                    throw new DukeException(MSG_ERR_EVENT_TO);
                }
                break;
            case MARK:
                switch (errorMessage) {
                case "Empty Index":
                    throw new DukeException(MSG_ERR_MARK_EMPTY);
                case "Out of Bound":
                    throw new DukeException(MSG_ERR_MARK_BOUND);
                case "Not Integer":
                    throw new DukeException(MSG_ERR_MARK_NONINT);
                }
                break;
            case UNMARK:
                switch (errorMessage) {
                case "Empty Index":
                    throw new DukeException(MSG_ERR_UNMARK_EMPTY);
                case "Out of Bound":
                    throw new DukeException(MSG_ERR_UNMARK_BOUND);
                case "Not Integer":
                    throw new DukeException(MSG_ERR_UNMARK_NONINT);
                }
                break;
            case DELETE:
                switch (errorMessage) {
                case "Empty Index":
                    throw new DukeException(MSG_ERR_DELETE_EMPTY);
                case "Out of Bound":
                    throw new DukeException(MSG_ERR_DELETE_BOUND);
                case "Not Integer":
                    throw new DukeException(MSG_ERR_DELETE_NONINT);
                }
                break;
            case LIST:
                switch (errorMessage) {
                case "Empty List":
                    throw new DukeException(MSG_ERR_LIST_EMPTY);
                case "Unbalanced List":
                    throw new DukeException(MSG_ERR_LIST_ECHO);
                }
                break;
            case TIME:
                switch (errorMessage) {
                case "DateTime Parse Exception":
                    throw new DukeException(MSG_ERR_TIME_PARSE);
                case "Deadline reached":
                    throw new DukeException(MSG_ERR_TIME_DEADLINE);
                case "Event Ended":
                    throw new DukeException(MSG_ERR_TIME_EVENT_OVER);
                case "Invalid Event Duration":
                    throw new DukeException(MSG_ERR_TIME_EVENT_INVALID);
                }
                break;
            case FILE:
                switch (errorMessage) {
                case "IOException Create":
                    throw new DukeException(MSG_ERR_FILE_CREATE);
                case "IOException Save":
                    throw new DukeException(MSG_ERR_FILE_SAVE);
                case "IOException Load":
                    throw new DukeException(MSG_ERR_FILE_LOAD);
                case "Incorrect Save Format":
                    throw new DukeException(MSG_ERR_FILE_FORMAT);
                }
                break;
        }
        } catch (IllegalArgumentException e) {
            throw new DukeException("Invalid Error Type");
        } catch (NullPointerException e) {
            throw new DukeException("Error Type cannot be empty");
        }
    }

    /**
     * Prints error message for unimplemented exceptions
     *
     * @param e The unimplemented exception
     * @return String array of the error message to be printed to console
     */
    public static String[] unimplemented(Exception e) {
        return new String[]{MSG_ERR_DEFAULT,
                "Error Message ("
                        + e.getClass().getName()
                        + "):",
                e.getMessage()};
    }
}
