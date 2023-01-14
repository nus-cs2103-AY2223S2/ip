package duke.constant;

public class Message {
    /* Exception constants */
    public static final String EXCEPTION_NOSUCH_COMMAND = "I'm sorry, but I don't know what that means :-(";
    public static final String EXCEPTION_INVALID_TASK_ID = "Invalid task id! Task id must be a number.";
    public static final String EXCEPTION_INVALID_TODO_ID_ACCESS = "Unable to find task.";
    public static final String EXCEPTION_INVALID_TODO_CMD = "The description of a todo cannot be empty.";
    public static final String EXCEPTION_INVALID_EVENT_CMD = "Invalid event command format.";
    public static final String EXCEPTION_INVALID_DEADLINE_CMD = "Invalid deadline command format.";
    public static final String EXCEPTION_INVALID_TASK_TYPE = "Invalid task type, please verify data file.";
    public static final String EXCEPTION_INVALID_DATE_CMD = "Invalid date command format.";
    public static final String EXCEPTION_INVALID_CMD_ARGS = "Invalid command arguments.";
    public static final String EXCEPTION_INVALID_DATE_FORMAT = "Invalid date argument format.";

    public static final String LIST_TASKS = "Here are the tasks in your list:";
    public static final String BYE = "Bye. Hope to see you again soon!";
    public static final String MARK_TASK = "Nice! I've marked this task as done:";
    public static final String UNMARK_TASK = "OK, I've marked this task as not done yet:";
    public static final String ADD_TASK = "Got it. I've added this task:";
    public static final String DELETE_TASK = "Noted. I've removed this task:";
    public static final String COUNT_TASK = "Now you have %d tasks in the list.";
    
}
