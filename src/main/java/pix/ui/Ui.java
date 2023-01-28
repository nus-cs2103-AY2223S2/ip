package pix.ui;

import java.time.LocalDate;

import pix.data.MyData;
import pix.tasks.Deadline;
import pix.tasks.Event;
import pix.tasks.Task;

/**
 * Ui class which deals with the interactions with user.
 */
public class Ui {
    /** Visual logo of Pix. */
    private static final String logo = "    ______    __  ___   ___ \n"
            + "    |   _  \\  |  | \\  \\ /  / \n"
            + "    |  |_)  | |  |  \\  V  /  \n"
            + "    |   ___/  |  |   >   <   \n"
            + "    |  |      |  |  /  .  \\  \n"
            + "    | _|      |__| /__/ \\__\\\n";

    /** Pix's greeting. */
    private static final String greet = "    --------------------------------------------------------------\n"
            + "     How can I assist you?\n"
            + "    --------------------------------------------------------------";

    /** Line in string. */
    private static final String line = "    --------------------------------------------------------------\n";

    /**
     * Displays Pix's introduction.
     */
    public static void display() {
        System.out.println("    Hello, this is\n" + logo);
        System.out.println(greet);
    }

    /**
     * Wraps the message in lines to display.
     *
     * @param message Message to be displayed.
     * @return A String with the message wrapped in lines.
     */
    public static String wrapLines(String message) {
        return "    --------------------------------------------------------------\n"
                + "     " + message + "\n"
                + "    --------------------------------------------------------------";
    }

    /**
     * Returns a line.
     *
     * @return A String line.
     */
    public static String line() {
        return line;
    }

    /**
     * Prints bye.
     */
    public void bye() {
        System.out.print(Ui.line()
                + "     Bye. Hope to see you again soon!\n"
                + Ui.line());
    }

    /**
     * Prints task added and number of tasks.
     *
     * @param description Description of task added.
     * @param listLen Number of tasks after adding tasks.
     */
    public void add(String description, int listLen) {
        System.out.print(Ui.line()
                + "     Okay! I've added this task:\n"
                + "       " + description + "\n"
                + "     Now you have " + listLen + " tasks in the list.\n"
                + Ui.line());
    }

    /**
     * Prints tasks deleted and number of tasks after deletion.
     *
     * @param task Task to be deleted.
     * @param listLen Number of tasks after deleting task.
     */
    public void delete(Task task, int listLen) {
        System.out.print(Ui.line()
                + "     Understood! The following task is now gone:\n"
                + "       " + task + "\n"
                + "     Now you have " + listLen + " tasks in the list.\n"
                + Ui.line());
    }

    /**
     * Lists all tasks.
     *
     * @param data Data containing ArrayList of tasks.
     */
    public void list(MyData data) {
        System.out.print(Ui.line());
        for (int i = 0; i < data.len(); i++) {
            System.out.printf("     %d. %s%n", i + 1, data.getData(i));
        }
        System.out.print(Ui.line());
    }

    /**
     * Lists tasks that occur or is active during the given date.
     *
     * @param data Data containing ArrayList of tasks.
     * @param date Date of tasks to list.
     */
    public void listDate(MyData data, LocalDate date) {
        System.out.print(Ui.line());
        for (int i = 0; i < data.len(); i++) {
            Task task = data.getData(i);
            if (task instanceof Deadline) {
                if (((Deadline) task).getDate().equals(date)) {
                    System.out.printf("     %d. %s%n", i + 1, data.getData(i));
                }
            }
            if (task instanceof Event) {
                Event taskEvent = (Event) task;
                if (date.isAfter(taskEvent.getFromDate()) && date.isBefore(taskEvent.getToDate())) {
                    System.out.printf("     %d. %s%n", i + 1, data.getData(i));
                }
            }
        }
        System.out.print(Ui.line());
    }

    /**
     * Prints that task is marked.
     *
     * @param task Task to be marked.
     */
    public void mark(Task task) {
        System.out.print(Ui.line()
                + "     Well done! You have completed the following task:\n"
                + "     " + task + "\n"
                + Ui.line());
    }

    /**
     * Prints that task is unmarked.
     *
     * @param task Task to un-mark.
     */
    public void unmark(Task task) {
        System.out.print(Ui.line()
                + "     Stop procrastinating and complete the following task:\n"
                + "     " + task + "\n"
                + Ui.line());
    }


    /**
     * Finds tasks that contain the keyword.
     *
     * @param data Data containing ArrayList of tasks.
     * @param keyword Keyword to search tasks by.
     */
    public void find(MyData data, String keyword) {
        System.out.print(Ui.line());
        for (int i = 0; i < data.len(); i++) {
            if (data.getData(i).inDescription(keyword)) {
                System.out.printf("     %d. %s%n", i + 1, data.getData(i));
            }
        }
        System.out.print(Ui.line());
    }
}
