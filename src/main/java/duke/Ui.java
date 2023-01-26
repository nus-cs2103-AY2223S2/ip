package duke;

import duke.task.Task;

/**
 * Handles the user interface and overall appearance of the duke program
 * @author oliverloo
 * @version 1.0
 *
 */
public class Ui {
    static String line = "      _____________________________________________________________________";

    /**
     * Greet user interface: Duke greets the user
     */
    public static void greet () {
        System.out.println(line);
        System.out.println("\n      Hello! I'm Oli\n" + "       What can I do for you?");
        System.out.println(line);
    }

    /**
     * Displays list to the user in a numbered format
     */
    public static void displayList(Tasklist list) {
        System.out.println(line);
        for (int i = 0; i < list.size(); i++) {
            System.out.println("        " + (i + 1) + ". " + list.get(i));
        }
        System.out.println(line);
    }

    /**
     * Displays a task that is just marked
     * @param task
     */
    public static void displayMark(Task task) {
        System.out.println("        Nice! I've marked this task as done:");
        System.out.println("        " + task);
    }

    /**
     * Displays a task that is just unmarked
     * @param task
     */
    public static void displayUnmark(Task task) {
        System.out.println("        OK, I've marked this task as not done yet:");
        System.out.println("        " + task);
    }

    /**
     * Bids the user farewell before terminating the duke program
     */
    public static void displayByeMessage() {
        System.out.println(line);
        System.out.println("        byebye! Have an exquisite day");
        System.out.println(line);
    }


    public static void displayInvalidIndexMessage() {
        System.out.println("Invalid Index!");
    }

    public static void displayDeadlineError() {
        System.out.println("Duke.Deadline Input Error! You need to specify date or content is empty!");
    }

    public static void displayEventError() {
        System.out.println("Invalid Input! You need to specify a /from and /to or content is empty!");
    }

    /**
     * Displays a task that is just added to a specified Tasklist
     * @param task
     * @param list
     */
    public static void displayAddTask(Task task, Tasklist list) {
        System.out.println(line);
        System.out.println("        Got it. I've added this task:");
        System.out.println("        " + task);
        displayUpdatedList(list);
    }

    /**
     * Displays an updated Tasklist
     */
    public static void displayUpdatedList(Tasklist list) {
        System.out.println("        Now you have " + list.size() + " tasks in the list.");
        System.out.println(line);
    }

    /**
     * Displays information of task that is just removed from Tasklist
     */
    public static void displayDelete(int i, Tasklist list){
        System.out.println(line);
        System.out.println("        Got it. I've removed this task:");
        System.out.println("        " + list.get(i));
        list.delete(i);
        System.out.println("        Now you have " + list.size() + " tasks in the list.");
        System.out.println(line);
    }

    /**
     * Informs user that list is just cleared
     */
    public static void displayClear() {
        System.out.println("        Got it. I have cleared the task-list.\n" + line);
    }
}
