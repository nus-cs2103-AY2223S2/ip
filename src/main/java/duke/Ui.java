package duke;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Encapsulates the messages seen in the User Interface.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Creates a new UI object.
     */
    Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Returns the user input.
     * @return The string format of the scanned user input.
     */
    String readInput() {
        return scanner.nextLine();
    }

    /**
     * Prints the Duke logo.
     */
    void showDuke() {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    /**
     * Closes the scanner.
     */
    void close() {
        scanner.close();
    }

    /**
     * Prints a long line
     */
    public void printLongLine() {
        System.out.println("\t____________________________________________________________");
    }

    /**
     * Prints a formatted message.
     * @param s The string input to be printed.
     */
    public void printMessage(String s) {
        printLongLine();
        System.out.println("\t" + s);
        printLongLine();
    }

    /**
     * Prints a greeting message to the user.
     */
    public void greetingMessage() {
        printMessage("Hello! I'm Duke\n\tWhat can I do for you?");
    }

    /**
     * Prints a goodbye message to the user.
     */
    public void goodbyeMessage() {
        printMessage("Bye. Hope to see you again soon!");
    }

    /**
     * Prints a formatted message showing that a task has been added to the list.
     * @param t The Task object to be added to the list.
     * @param size The size of the list after adding the new Task.
     */
    public void addedTaskMessage(Task t, int size) {
        printLongLine();
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t" + t);
        System.out.println("\tNow you have " + size + " tasks in the list.");
        printLongLine();
    }

    /**
     * Prints a formatted message showing that a task has been deleted from the list.
     * @param t The Task object to be deleted from the list.
     * @param size The size of the list after the task has been deleted.
     */
    public void deletedTaskMessage(Task t, int size) {
        printLongLine();
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t" + t);
        System.out.println("\tNow you have " + size + " tasks in the list");
        printLongLine();
    }

    /**
     * Prints a message to confirm that the task has been marked as done.
     * @param t The Task object to be marked as done.
     */
    public void markTaskAsDoneMessage(Task t) {
        printMessage("Nice! I've marked this task as done:\n\t" + t);
    }

    /**
     * Prints a message to confirm that the task has been marked as incomplete.
     * @param t The Task object to be marked as incomplete.
     */
    public void markTaskAsIncompleteMessage(Task t) {
        printMessage("OK, I've marked this task as not done yet:\n\t" + t);

    }

    /**
     * Prints the content of ArrayList of Task objects.
     * @param tasks The ArrayList of Tasks to be printed.
     */
    public void printList(ArrayList<Task> tasks) {
        printLongLine();
        for (int i = 0; i < tasks.size(); i++) {
            int taskNumber = i + 1;
            System.out.printf("\t%d. %s\n", taskNumber, tasks.get(i));
        }
        printLongLine();
    }
}
