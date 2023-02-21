package wessy.components;

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
    public String printByeMessage() {
        return printMessage("Bye. Hope to see you again soon!\n");
    }

    /**
     * Prints output message for "list" command.
     *
     * @param tasks
     * @param isList
     */
    public String printListOrFindMessage(String[] tasks, boolean isList) {
        return printMessage(printListOrFind(tasks, isList));
    }

    /**
     * A helper function used in pritnListMessage(...).
     *
     * @param tasks
     * @param isList
     */
    String printListOrFind(String[] tasks, boolean isList) {
        String extraWord = isList ? "" : "matching ";
        if (tasks.length == 0) {
            if (isList) {
                return "WOOHOO! You do not have any task on the list.\n";
            } else {
                return "Sorry. We did not find any task that matches the text you entered.\n";
            }
        } else {
            return "Here are the " + extraWord + "tasks in your list:\n" + printMultiln(tasks);
        }
    }

    /**
     * Prints output message for "todo", "deadline" and "event" commands.
     *
     * @param task
     * @param size
     */
    public String printAddedMessage(Task task, int size) {
        String numOfTasks = " " + size + " task";
        if (size > 1) {
            numOfTasks += "s";
        }
        return printMessage("Got it. I've added this task:", "  " + task, "Now you have" + numOfTasks
                + " in the list.");
    }

    /**
     * Prints output message for "mark" and "unmark" commands.
     *
     * @param chosenTask
     * @param isMark
     */
    public String printMarkUnmarkMessage(Task chosenTask, boolean isMark) {
        String start = isMark ? "Nice! I've" : "OK, I've";
        if (isMark == chosenTask.checkIsDone()) {
            start = "You have already";
        }
        String end = isMark ? "done:" : "not done yet:";
        return printMessage(start + " marked this task as " + end, "  " + chosenTask);
    }

    /**
     * Prints output message for "delete" command.
     *
     * @param chosenTask
     * @param totalNumOfTasks
     */
    public String printDeleteMessage(Task chosenTask, int totalNumOfTasks) {
        return printMessage("Noted. I've removed this task:", "  " + chosenTask, String.format(
                "Now you have %d task%s in the list.", totalNumOfTasks, totalNumOfTasks == 1 ? "" : "s"));
    }

    /**
     * Prints output message for "clear" command.
     */
    public String printClearMessage() {
        return printMessage("You have cleared your task list. The list is empty now.");
    }

    /**
     * Prints output message when starting up Wessy.
     *
     * @param tasks
     */
    public String printWelcomeMessage(String[] tasks) {
//        printOpening();
        return "Hi, I am Wessy, your personal assistant chatbot.\n\n" +
        printListOrFind(tasks, true);
//        printClosing();
    }

//    /**
//     * A helper function
//     */
//    private static void printOpening() {
//        System.out.println("    -Wessy------------------------------" +
//                "---------------------------------- ");
//    }
//
//    /**
//     * A helper function
//     */
//    private static void printClosing() {
//        System.out.println("    -----------------------------------" +
//                "----------------------------------- ");
//    }

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
    private String printMultiln(String... linesOfString) {
        String str = "";
        for (String line : linesOfString) {
            str += line + "\n";
        }
        return str;
    }

    /**
     * A helper function
     *
     * @param linesOfString
     */
    public String printMessage(String... linesOfString) {
        return OPENING + printMultiln(linesOfString);
    }

    /**
     * @param message
     * @return
     */
    public String handleException(String message) {
//        System.err.println("    -Wessy---------------------------------------------------------------- ");
        return printMessage("ERROR! " + message);
//        System.err.println("    ---------------------------------------------------------------------- ");
    }
}
