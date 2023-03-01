package duke;


/**
 * Class responsible for Duke's response messages.
 */
public class MessageGenerator {

    /**
     * get Duke's welcome message that plays on startup.
     */
    public static String genWelcomeMsg() {
        return "What's up? \uD83D\uDE00";
    }

    public static String genByeMsg() {
        return "Goodbye";
    }

    public static String genAddedTaskMsg(String task) {
        return "added a " + task;
    }

    public static String genMissingTaskDescMsg(String task) {
        return "You're missing a description for your " + task;
    }

    public static String genMissingFieldMsg(String field) {
        return "You're missing a " + field + " field";
    }

    public static String genInvalidFieldMsg(String field) {
        return field + " is not a valid field.";
    }

    public static String genDateTimeParseErrorMsg() {
        return "Couldn't understand the given date and time";
    }

    public static String genDeleteTaskMsg(String task) {
        return "Deleted " + task;
    }

    public static String genTaskDoesNotExistMsg(String number) {
        return "A task with number " + number + " does not exist.";
    }

    public static String genNotANumberMsg() {
        return "We needed a number here";
    }

    /**
     * Generates a message containing a list of tasks If tasks were found.
     * Else, generate a did not find anything message.
     *
     * @param tasks a list of tasks
     * @return a message containing the tasks found
     */
    public static String genFindTasksMsg(String tasks) {
        if (tasks.isEmpty()) {
            return "Didn't find anything";
        }

        return "Here's what I found:\n" + tasks;
    }

    /**
     * Generates a message containing a list of tasks. If there are no tasks in the list, generate an empty task list
     * message.
     *
     * @param tasks a list of tasks
     * @return a message containing the tasks found
     */
    public static String genShowTasksMsg(String tasks) {
        if (tasks.isEmpty()) {
            return "You don't have any tasks... Try adding some.";
        }

        return "Here's your tasks:\n" + tasks;
    }

    /**
     * Generates a message indicating the task has been marked or unmarked.
     *
     * @param task
     * @param isMark
     * @return
     */
    public static String genMarkorUnmarkTaskMsg(String task, boolean isMark) {
        if (isMark) {
            return "Marked this task: " + task;
        }

        return "Unmarked this task: " + task;
    }

    public static String genEditTaskMsg(String task) {
        return "Edited this task: " + task;
    }

    public static String genUnknownCommandMsg() {
        return "I don't know what that means";
    }
}
