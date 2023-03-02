package wessy.components;

import java.util.Arrays;
import java.util.Scanner;

import wessy.task.Task;

/**
 *
 */
public class Ui {
    private final static String OPENING = "Wessy:\n";
    private final Scanner sc;

    /**
     * Constructs an instance of Ui.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Handles inputs.
     *
     * @return A boolean value
     */
    public boolean hasNextLine() {
        return sc.hasNextLine();
    }

    /**
     * Handles inputs.
     *
     * @return
     */
    public String readNextLine() {
        return sc.nextLine();
    }

    /**
     * Prints output message for "bye" command.
     */
    public String getByeMessage() {
        return getMessage("Bye. Hope to see you again soon!\n");
    }

    /**
     * Prints output message for "list" command.
     *
     * @param tasks
     * @param isList
     */
    public String getListOrFindMessage(String[] tasks, boolean isList) {
        return getMessage(getListOrFindContent(tasks, isList));
    }

    /**
     * A helper function used in pritnListMessage(...).
     *
     * @param tasks
     * @param isList
     */
    private String getListOrFindContent(String[] tasks, boolean isList) {
        String extraWord = isList ? "" : "matching ";
        if (tasks.length == 0) {
            if (isList) {
                return "WOOHOO! You do not have any task on the list.\n";
            } else {
                return "Sorry. We did not find any task that matches the text you entered.\n";
            }
        } else {
            return "Here are the " + extraWord + "tasks on your list:\n" + getMultiln(tasks);
        }
    }

    /**
     * Prints output message for "todo", "deadline" and "event" commands.
     *
     * @param task
     * @param size
     */
    public String getAddedMessage(Task task, int size) {
        String numOfTasks = " " + size + " task";
        if (size > 1) {
            numOfTasks += "s";
        }
        return getMessage("Got it. I've added this task:", "  " + task, "Now you have" + numOfTasks
                + " on the list.");
    }

    /**
     * Prints output message for "mark" and "unmark" commands.
     *
     * @param chosenTask
     * @param isDone
     */
    public String getMarkUnmarkMessage(Task chosenTask, boolean isDone) {
        String end = isDone ? "done already:" : "not done yet:";
        return getMessage("You mark this task as " + end, "  " + chosenTask);
    }

    /**
     * Prints output message for "delete" command.
     *
     * @param chosenTask
     * @param totalNumOfTasks
     */
    public String getDeleteMessage(Task chosenTask, int totalNumOfTasks) {
        return getMessage("Noted. I've removed this task:", "  " + chosenTask, String.format(
                "Now you have %d task%s on the list.", totalNumOfTasks, totalNumOfTasks == 1 ? "" : "s"));
    }

    /**
     * Prints output message for "clear" command.
     */
    public String getClearMessage() {
        return getMessage("You have cleared your task list. The list is empty now.");
    }

    /**
     * Prints output message when starting up Wessy.
     *
     * @param tasks
     */
    public String getWelcomeMessage(String[] tasks) {
        return "Hi, I am Wessy, your personal assistant chatbot.\n\n" +
        getListOrFindContent(tasks, true);
    }
    
    /**
     * A helper function
     *
     * @param str
     */
    private void println(String str) {
        int length = str.length();
        if (length <= 64) {
            String message = "   |   " + str;
            message += " ".repeat(67 - length) + "|";
            System.out.println(message);
        } else {

            System.out.println("   |   " + str.substring(0, 64) + "   |");

            int remainingLength = length - 64;
            int leftover = remainingLength % 62;
            int n = (int) Math.floor(remainingLength/62);

            for (int i = 0; i < n; i++) {
                System.out.println("   |     " + str.substring(62 * i, 62 * (i + 1)) + "   |");
            }

            String message = "   |     " + str.substring(length - leftover);
            message += " ".repeat(65 - leftover) + "|";
            System.out.println(message);
        }
    }

    /**
     * A helper function
     *
     * @param str
     */
    private void printErr(String str) {
        int length = str.length();
        if (length <= 64) {
            String message = "   |   " + str;
            message += " ".repeat(68 - length) + "|";
            System.err.println(message);
        } else {

            System.err.println("   |   " + str.substring(0, 64) + "    |");

            int remainingLength = length - 64;
            int leftover = remainingLength % 62;
            int n = (int) Math.floor(remainingLength/62);

            for (int i = 0; i < n; i++) {
                System.err.println("   |     " + str.substring(62 * i, 62 * (i + 1)) + "    |");
            }

            String message = "   |     " + str.substring(length - leftover);
            message += " ".repeat(65 - leftover) + "|";
            System.err.println(message);
        }
    }

    /**
     * A helper function
     *
     * @param linesOfString
     */
    private String getMultiln(String... linesOfString) {
        return Arrays.stream(linesOfString).reduce("", (curr, next) -> curr + next + "\n");
    }

    /**
     * A helper function
     *
     * @param linesOfString
     */
    public String getMessage(String... linesOfString) {
        return OPENING + getMultiln(linesOfString);
    }

    /**
     * @param message
     * @return
     */
    public String handleException(String message) {
        return getMessage("ERROR! " + message);
    }
}
