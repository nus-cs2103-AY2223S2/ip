package duke.constant;

/**
 * Message strings of duke,
 */
public class Message {
    /* Exception messages */
    public static final String EXCEPTION_INVALID_CMD_ARGS = "Invalid command arguments.";
    public static final String EXCEPTION_INVALID_TODO_CMD = "The description of a todo cannot be empty.";
    public static final String EXCEPTION_INVALID_EVENT_CMD = "Invalid event command.";
    public static final String EXCEPTION_INVALID_DEADLINE_CMD = "Invalid deadline command.";
    public static final String EXCEPTION_INVALID_DATE_CMD = "Invalid date command.";
    public static final String EXCEPTION_INVALID_MARK_CMD = "Invalid mark command.";
    public static final String EXCEPTION_INVALID_UNMARK_CMD = "Invalid unmark command.";
    public static final String EXCEPTION_INVALID_DELETE_CMD = "Invalid delete command.";
    public static final String EXCEPTION_NOSUCH_COMMAND = "I'm sorry, but I don't know what that means :-(";

    public static final String EXCEPTION_INVALID_TASK_ID_FORMAT = "Invalid task id! Task id must be a number.";
    public static final String EXCEPTION_INVALID_TASK_ID_ACCESS = "Unable to find task.";

    public static final String EXCEPTION_INVALID_TASK_TYPE = "Invalid task type, please verify data file.";
    public static final String EXCEPTION_INVALID_DATE_FORMAT = "Invalid date argument format.";

    public static final String EXCEPTION_DB_CLOSED = "Database closed.";

    /* Normal messages */
    public static final String WELCOME = "Hello from Agent™\n"
                                        + " ____       _\n"
                                        + "|  _ \\ _  _| | ____ _\n"
                                        + "| | | | | |  | |/ / _ \\\n"
                                        + "| |_| | |_|  |   <  __/\n"
                                        + "|____/ \\__,_|_|\\_\\___|\n"
                                        + "What can I do for you?";
    public static final String LIST_TASKS = "Here are the tasks in your list:";
    public static final String BYE = "👋. Hope to see you again soon!";
    public static final String MARK_TASK = "✅ I've marked this task as done:";
    public static final String UNMARK_TASK = "OK, I've marked this task as not done yet:";
    public static final String ADD_TASK = "👍 I've added this task:";
    public static final String DELETE_TASK = "Noted. I've removed this task:";
    public static final String COUNT_TASK = "Now you have %d tasks in the list.";
    public static final Object LIST_EMPTY = "No task found in the list.";
    public static final Object FIND_TASKS = "Here are the matching tasks in your list:";
    public static final String PARTIAL_DELETE = "‼ %d task not found, no action taken.";
    public static final String UI_TITLE = "Agent™ Duke";

}
