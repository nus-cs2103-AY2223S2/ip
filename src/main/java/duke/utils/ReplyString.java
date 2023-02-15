package duke.utils;

import duke.tasks.Task;

/**
 * Stores and handles any string literal or operations related to Duke.
 */
public class ReplyString {

    private static final String LOGO = " ____        _        \n"
                                + "|  _ \\ _   _| | _____ \n"
                                + "| | | | | | | |/ / _ \\\n"
                                + "| |_| | |_| |   <  __/\n"
                                + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String INITIAL_GREETING = "Hope you are doing great!\nWhat can I do for you?\n";
    private static final String DOTTED_LINE = "----------------------------------------------------\n";
    private static final String ON_STARTUP = LOGO + INITIAL_GREETING + DOTTED_LINE;
    private static final String PROMPT_QUESTION = "Enter your prompt below:";
    private static final String GOODBYE = "Sad...Alright bye!";

    public static String getOnStartupString() {
        return ON_STARTUP;
    }

    public static String getPromptQuestionString() {
        return PROMPT_QUESTION;
    }

    public static String getGoodbyeString() {
        return GOODBYE;
    }

    /**
     * Outputs the string representation of all tasks within the
     * task list provided as input.
     *
     * @param taskList the TaskList containing relevant tasks.
     * @return A string representation of all tasks within the provided task list.
     */
    public static String getTaskListString(TaskList taskList) {
        StringBuilder result = new StringBuilder();
        result.append("\n\n");
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.getTask(i);
            String taskString = String.format("%d. %s", i + 1, task.toString());
            result.append(taskString);
            result.append("\n");
        }
        result.append("\n");
        return result.toString();
    }

    /**
     * Outputs the string representation of all tasks within the user's task list.
     *
     * @param allTasks the TaskList containing all tasks logged by the user.
     * @return A string representation of all tasks within the user's task list.
     */
    public static String getAllTasksString(TaskList allTasks) {
        if (allTasks.size() == 0) {
            return "You have zero tasks now!";
        }
        StringBuilder result = new StringBuilder();
        result.append("Your tasks so far!!");
        result.append(getTaskListString(allTasks));
        return result.toString();
    }


}
