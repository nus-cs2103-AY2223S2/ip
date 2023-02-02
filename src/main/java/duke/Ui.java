package duke;
import java.util.ArrayList;

public class Ui {
    /**
     * Print intro.
     */
    public static String intro() {
        return "Hello! I am your anime waifu!\n" +
                "What can I do for you my husbando?\n" +
                " (*_*)\n" +
                "|(   )|\n" +
                "  |-|\n";
    }

    /**
     * Print closing statement.
     */
    public static void close() {
        System.out.println("Bye! Hope to see you again <3!");
    }

    /**
     * Show invallid Command.
     */
    public static String invalidCommand() {
        return "please make sure your command is valid!\n";
    }

    /**
     * Show missing args.
     */
    public static String missingArgs() {
        return "please ensure there are arguments for particular commands!\n";
    }

    /**
     * List out task content.
     * @param tasks
     */
    public static String list(ArrayList<Task> tasks) {
        String result = "Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            result += String.format("%d.%s%n", i + 1, tasks.get(i));
        }
        return result;
    }
    /**
     * Show found tasks
     * @param tasks
     */
    public static String find(ArrayList<Task> tasks) {
        String result = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            String.format("%d.%s%n", i + 1, tasks.get(i));
        }
        return result;
    }

    /**
     * Show marked task.
     * @param isMark
     * @param task
     */
    public static String mark(boolean isMark, Task task) {
        String result = "";
        if (isMark) {
            result += "Nice! I've marked this task as done:\n";
        } else {
            result += "OK, I've marked this task as not done yet:\n";
        }
        return result + task.toString() + "\n";
    }

    /**
     * Show NAN error.
     */
    public static String notANumber() {
        return "please only input numbers\n";
    }

    /**
     * Show IndexOutOfBounds error.
     */
    public static String numberOutOfBounds() {
        return "make sure the number is in range\n";
    }

    /**
     * Show added Task.
     * @param type
     * @param task
     */
    public static String addTask(String type, Task task) {
        String result = String.format("Got it I've added a %s%n", type);
        return result + task.toString() + "\n";
    }

    /**
     * Show missing args.
     * @param option
     */
    public static String missingOptions(String option) {
        return String.format(
                "ensure %s option(s) and %s option's argument exist (in that order if command has multiple option(s))%n",
                option);
    }

    /**
     * Show wrong date format.
     */
    public static String wrongDateFormat() {
        return "please ensure yyyy-MM-dd format\n";
    }

    /**
     * Show deleted task.
     * @param task
     */
    public static String delete(Task task) {
        String result = "I have removed this task\n";
        return result + task;
    }
}
