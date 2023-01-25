package duke.ui;

import duke.data.MyData;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;

import java.time.LocalDate;

public class Ui {
    /** Visual logo of duke. */
    private static final String logo = "     ____        _\n"
            + "    |  _ \\ _   _| | _____\n"
            + "    | | | | | | | |/ / _ \\\n"
            + "    | |_| | |_| |   <  __/\n"
            + "    |____/ \\__,_|_|\\_\\___|\n";

    /** Duke's greeting. */
    private static final String greet = "    ____________________________________________________________\n"+
            "    Hello! I'm Duke\n" +
            "    What can I do for you?\n" +
            "    ____________________________________________________________";

    private static final String line = "    ____________________________________________________________\n";

    /**
     * Displays duke's introduction.
     */
    public static void display() {
        System.out.println("    Hello from\n" + logo);
        System.out.println(greet);
    }

    public static String wrapLines(String message) {
        return "    ____________________________________________________________\n" +
                "    " + message + "\n" +
                "    ____________________________________________________________";
    }

    public static String line() {
        return line;
    }

    /**
     * Prints bye.
     */
    public void bye() {
        System.out.print(Ui.line() +
                "    Bye. Hope to see you again soon!\n" +
                Ui.line());
    }

    /**
     * Prints task added and number of tasks.
     *
     * @param description Description of task added.
     * @param listLen Number of tasks after adding tasks.
     */
    public void add(String description, int listLen) {
        System.out.print(Ui.line() +
                "     Got it. I've added this task:\n" +
                "       " + description + "\n" +
                "     Now you have " + listLen + " tasks in the list.\n" +
                Ui.line());
    }

    /**
     * Prints tasks deleted and number of tasks after deletion.
     *
     * @param task Task to be deleted.
     * @param listLen Number of tasks after deleting task.
     */
    public void delete(Task task, int listLen) {
        System.out.print(Ui.line() +
                "     Noted. I've removed this task:\n" +
                "       " + task + "\n" +
                "     Now you have " + listLen + " tasks in the list.\n" +
                Ui.line());
    }

    /**
     * Lists all tasks.
     *
     * @param data Data containing ArrayList of tasks.
     */
    public void list(MyData data) {
        System.out.print(Ui.line());
        for (int i = 0; i < data.len(); i++) {
            System.out.printf("    %d. %s%n", i + 1, data.getData(i));
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
                    System.out.printf("    %d. %s%n", i + 1, data.getData(i));
                }
            }
            if (task instanceof Event) {
                Event taskEvent = (Event) task;
                if (date.isAfter(taskEvent.getFromDate()) && date.isBefore(taskEvent.getToDate())) {
                    System.out.printf("    %d. %s%n", i + 1, data.getData(i));
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
        System.out.print(Ui.line() +
                "    Nice! I've marked this task as done:\n" +
                "    " + task + "\n" +
                Ui.line());
    }

    /**
     * Prints that task is unmarked.
     *
     * @param task Task to un-mark.
     */
    public void unmark(Task task) {
        System.out.print(Ui.line() +
                "    OK, I've marked this task as not done yet:\n" +
                "    " + task + "\n" +
                Ui.line());
    }
}
