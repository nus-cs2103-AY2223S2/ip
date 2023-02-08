package runner;
import task.Task;

/**
 * Class for Ui object.
 */
public class Ui {
    static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    /**
     * Words shown when initiating.
     */
    public static void start() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?" );
        System.out.println(logo);
    }

    /**
     * Words shown when terminating.
     */
    public static void ending() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(logo);
    }

    /**
     * Words shown when marking a Task.
     * @param t Task to be marked.
     */
    public static void markMSG(Task t) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("[X] " + t.getMsg());
    }

    /**
     * Words shown when unmarking a Task.
     * @param t Task to be unmarked.
     */
    public static void unmarkMSG(Task t) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("[ ] " + t.getMsg());
    }


    /**
     * Words shown when deleting a Task.
     * @param t Task to be deleted.
     * @param n Tasks in TaskList afterwards.
     */
    public static void deleteMSG(Task t, int n) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(t);
        System.out.println("Now you have " + n + " tasks in the list.");
    }

    /**
     * Words shown when adding a Task.
     * @param t Task to be added.
     * @param n Tasks in TaskList afterwards.
     */
    public static void addMSG(Task t, int n) {
        System.out.println("Got it. I've added this task:");
        System.out.println(t);
        System.out.println("Now you have " + n + " tasks in the list.");
    }

    /**
     * Show the TaskList of a Duke.
     */
    public static void showList(TaskList t) {
        System.out.println("Here are the tasks in your list:");
        for (Task tk : t.get_list()) {
            System.out.println( (t.get_list().indexOf(tk)+1) + "." + tk.toString());
        }
    }

    /**
     * Show the TaskList after find keywords.
     */
    public static void findList(TaskList t) {
        System.out.println("Here are the matching tasks in your list:");
        for (Task tk : t.get_list()) {
            System.out.println( (t.get_list().indexOf(tk)+1) + "." + tk.toString());
        }
    }
}
