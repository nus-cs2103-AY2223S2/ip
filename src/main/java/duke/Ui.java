package duke;

import java.util.Scanner;

/**
 * Class responsible for Duke's response messages.
 */
public class Ui {

    /**
     * get Duke's welcome message that plays on startup.
     */
    public static String genWelcomeMsg() {
        return "What's up? \uD83D\uDE00";
    }

    public static String genUnknownCommandMsg() {
        return "Unknown command.";
    }

    public static String genGoodbyeMsg() {
        return "Goodbye.";
    }

    public static String genShowTaskListMsg(String taskListString) {
        return taskListString;
    }

    public static String genMarkTaskMsg(boolean isMark) {
        return (isMark) ? "Marked this task" : "Unmarked this task";
    }
    public static String genDeleteTaskMsg() {
        return "Deleted this task.";
    }

    public static String genMissingTaskNumberMsg() {
        return "Task number missing.";
    }

    public static String genTaskDoesNotExistMsg() {
        return "Task doesn't exist.";
    }

    public static String genAddTaskMsg(String msg) {
        return msg;
    }

    public static String genMissingDateFieldMsg() {
        return "You're missing a date.";
    }

    public static String genDateParseFailureMsg() {
        return "Couldn't parse the date.";
    }

    public static String genMissingKeywordMsg() {
        return "You're missing a keyword.";
    }

    public static String genFoundTasksMsg(String foundTasksString) {
        return foundTasksString;
    }
}
