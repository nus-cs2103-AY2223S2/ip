package support;

/**
 * Contains instructions to user.
 */
public class Instructions {

    /**
     * Prints a user guide when requested.
     * @return String representing user guide
     */
    public static String generate() {
        return "Here is a list of commands that you may use:\n"
                + "todo <task> - creates a new task\n"
                + "deadline <task> /<yyyy-mm-dd> - creates a task with an end date\n"
                + "event <task> /<yyyy-mm-dd> /<yyyy-mm-dd> - creates a task with start and end date\n"
                + "mark/unmark <int> - change status of task\n"
                + "delete <int> - deletes a task in the list\n"
                + "find <word> - returns tasks in list which matches word\n"
                + "missions - prints out a todo list of existing tasks\n"
                + "help - prints out this list of commmands\n"
                + "bye - to close program\n";
    }
}
