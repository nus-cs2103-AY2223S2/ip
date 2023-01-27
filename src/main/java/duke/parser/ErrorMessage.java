package duke.parser;

public class ErrorMessage {

    // Errors related to task description
    public static final String EMPTY_DEADLINE_ERROR = "OOPS!!! The description of a deadline cannot be empty.";
    public static final String EMPTY_EVENT_ERROR = "OOPS!!! The description of an event cannot be empty.";
    public static final String EMPTY_TODO_ERROR = "OOPS!!! The description of a todo cannot be empty.";
    public static final String INVALID_DESCRIPTION_ERROR = "OOPS!!! The description cannot be empty.";

    // Errors related to task index
    public static final String INVALID_INDEX_ERROR = "OOPS!!! The input index is not within the range of [1, %d]. "
            + "Please input a index that is within the given range";
    public static final String INVALID_TASK_INDEX_ERROR = "OOPS!!! The input task index is not a number. "
            + "Please input a valid task index.";

    // Errors related to date and time
    public static final String INVALID_DATE_ERROR = "OOPS!!! The input date format is invalid. "
            + "Please input the date in the format of yyyy-mm-dd.";
    public static final String INVALID_DEADLINE_FORMAT_ERROR = "OOPS!!! Please input the deadline in the "
            + "correct format. Example: deadline Assignment 1 /by 2023-12-31";
    public static final String INVALID_EVENT_FORMAT_ERROR = "OOPS!!! Please input the event in the "
            + "correct format. Example: event Team Meeting /from 2020-12-31 /to 2020-12-31";
    public static final String INVALID_FROM_AND_TO_ERROR = "OOPS!!! Start date can not be after than the End date";

    // Other errors
    public static final String ADD_MORE_TASKS = "\nPlease add in more tasks";
    public static final String EMPTY_ERROR = "OOPS!!! The instruction cannot be empty";
    public static final String NO_COMMAND_ERROR_MESSAGE = "No previous commands to undo.";
    public static final String STORAGE_ERROR = "OOPS!!! There's something wrong when reading the storage list";
    public static final String TASK_LIST_EMPTY_ERROR = "OOPS!!! Your task list is currently empty";
    public static final String UNRECOGNIZED_ERROR = "OOPS!!! I'm sorry, but I don't know what that means :-( "
            + "Please type in \"help\" to check all available commands.";
}
