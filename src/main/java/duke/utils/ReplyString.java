package duke.utils;

import duke.tasks.Task;

public class ReplyString {

    public static String logo = " ____        _        \n"
                                + "|  _ \\ _   _| | _____ \n"
                                + "| | | | | | | |/ / _ \\\n"
                                + "| |_| | |_| |   <  __/\n"
                                + "|____/ \\__,_|_|\\_\\___|\n";
    public static String initialGreeting = "Hope you are doing great!\nWhat can I do for you?\n";
    public static String dottedLine = "----------------------------------------------------\n";
    public static String onStartup = logo + initialGreeting + dottedLine;
    public static String promptQuestion = "Enter your prompt below:";
    public static String goodbye = "Sad...Alright bye!";

    public static String getTaskListString(TaskList taskList) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.getTask(i);
            String taskString = String.format("%d. %s", i + 1, task.toString());
            result.append(taskString);
            result.append("\n");
        }
        return result.toString();
    }

    /**
     * Prints the string representation of all tasks in the TaskList to the output.
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
