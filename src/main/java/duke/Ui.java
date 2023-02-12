package duke;
import java.util.ArrayList;

public class Ui {
    /**
     * Print intro.
     */
    public static String intro() {
        return "Hello! I am your anime waifu!\n" +
                "What can I do for you my husbando?\n" +
                "Do you want dinner?\n" +
                "Do you want a shower?\n" +
                "Or do you want mi <3 hehe \n" +
                " (*_*)\n" +
                "|(   )|\n" +
                "  |-|\n";
    }

    /**
     * Show invallid Command.
     */
    public static String invalidCommand() {
        return "please make sure your command is valid! kono bakayaro!!!\n"
                + "hint: use \"help\" command :D";
    }

    /**
     * Show missing args.
     */
    public static String missingArgs() {
        return "please ensure there are arguments for particular commands! b-b-b-BAKAAA!!! \n";
    }

    /**
     * List out task content.
     * @param tasks ArrayList of Task to be iterated
     */
    public static String list(ArrayList<Task> tasks) {
        String result = "Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            result = result.concat(String.format("%d.%s%n", i + 1, tasks.get(i)));
        }
        return result;
    }
    /**
     * Show found tasks
     * @param tasks ArrayList of Task to be iterated
     */
    public static String find(ArrayList<Task> tasks) {
        String result = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            result = result.concat(String.format("%d.%s%n", i + 1, tasks.get(i)));
        }
        return result;
    }

    /**
     * Show marked task.
     * @param isMark to mark done or undone
     * @param task task to be marked
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
     * @param type type of task
     * @param task task to be added
     */
    public static String addTask(String type, Task task) {
        String result = String.format("Got it I've added a %s%n", type);
        return result + task.toString() + "\n";
    }

    /**
     * Show missing args.
     * @param option option to be shown
     */
    public static String missingOptions(String option) {
        return String.format(
                "ensure %s option(s) and %s option's argument exist (in that order if command has multiple option(s))%n",
                option, option);
    }

    /**
     * Show wrong date format.
     */
    public static String wrongDateFormat() {
        return "please ensure yyyy-MM-dd format\n";
    }

    /**
     * Show deleted task.
     * @param task task to be deleted
     */
    public static String delete(Task task) {
        String result = "I have removed this task\n";
        return result + task;
    }

    /**
     * Print out help.
     * @return help string
     */
    public static String help() {
        return "Here are the commands LIST, FIND, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, HELP\n"
                + "only the list an help command do not have any arguments or additional inputs\n"
                + "Here are some examples on how the rest of the commands are called\n"
                + "find work -> looks through your list to find a task with a string word in it\n"
                + "mark/unmark/delete 4 -> mark, unmark or delete the fourth task\n"
                + "todo work -> adds a todo called work\n"
                + "deadline work /by 2023-08-30 -> adds a deadline called work\n"
                + "event work /from 2023-08-30 /to 2023-09-30-> adds a todo called work\n";
    }
}
