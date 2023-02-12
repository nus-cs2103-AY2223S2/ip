package duke.parser;

/**
 * A class that encapsulates all the error messages.
 */
public class ErrorMessage {
    // Errors related to task description
    public static final String EMPTY_DEADLINE_ERROR = "OOPS!!! The description of a deadline task cannot be empty.";
    public static final String EMPTY_EVENT_ERROR = "OOPS!!! The description of an event task cannot be empty.";
    public static final String EMPTY_TODO_ERROR = "OOPS!!! The description of a todo task cannot be empty.";
    public static final String EMPTY_FIXED_DURATION_DESCRIPTION_ERROR = "OOPS!!! The description of "
            + "a fixed duration task cannot be empty.";
    public static final String INVALID_DESCRIPTION_ERROR = "OOPS!!! The description cannot be empty.";
    public static final String INVALID_HELP_COMMAND_ERROR = "OOPS!!! Unrecognized help Command.\nPlease try:\n"
            + "- help\n- help date\n- help time\n- help duration";

    // Errors related to task index
    public static final String INVALID_INDEX_ERROR = "OOPS!!! The input index is not within the range of [1, %d]. "
            + "Please input a index that is within the given range";
    public static final String INVALID_TASK_INDEX_ERROR = "OOPS!!! The input task index is not a number. "
            + "Please input a valid task index.";

    // Errors related to date and time
    public static final String INVALID_DATE_ERROR = "OOPS!!! The input date format is invalid. "
            + "Type \"help date\" to check the date format";
    public static final String INVALID_DATETIME_ERROR = "OOPS!!! The input date time format is invalid. "
            + "Type \"help date\" to check the date time format";
    public static final String INVALID_DURATION_FORMAT = "OOPS!!! The input duration format is invalid. "
            + "Type \"help duration\" to check the date time format";
    public static final String INVALID_DEADLINE_FORMAT_ERROR = "OOPS!!! Please input the deadline task in the "
            + "correct format. Example: deadline Assignment 1 /by 15/01/2023 1500";
    public static final String INVALID_EVENT_FORMAT_ERROR = "OOPS!!! Please input the event task in the "
            + "correct format. Example: event Team Meeting /from 16/01/2023 1500 /to 16/01/2023 1900";
    public static final String INVALID_FIXED_DURATION_FORMAT_ERROR = "OOPS!!! Please input the fixed duration "
            + "task in the correct format. Example: fixed Time Practice /within PT15M";
    public static final String INVALID_FROM_AND_TO_ERROR = "OOPS!!! Start date can not be after than the End date";

    // Storage errors
    public static final String INVALID_TYPE_ERROR = "Type tag of event should be [T], [D], or [E]";
    public static final String INVALID_STATUS_ERROR = "IsDone tag of event should be [ ], or [X]";

    // Other errors
    public static final String ADD_MORE_TASKS = "\nPlease add in more tasks";
    public static final String EMPTY_ERROR = "OOPS!!! The instruction cannot be empty";
    public static final String STORAGE_ERROR = "OOPS!!! There's something wrong when reading the storage list";
    public static final String TASK_LIST_EMPTY_ERROR = "OOPS!!! Your task list is currently empty";
    public static final String UNRECOGNIZED_ERROR = "OOPS!!! I'm sorry, but I don't know what that means :-( "
            + "Please type in \"help\" to check all available commands.";
}
