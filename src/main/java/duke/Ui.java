package duke;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    Ui() {
        scanner = new Scanner(System.in);
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    String readInput() {
        return scanner.nextLine();
    }

    void close() {
        scanner.close();
    }

    public void printLongLine() {
        System.out.println("\t____________________________________________________________");
    }

    public void printMessage(String s) {
        printLongLine();
        System.out.println("\t" + s);
        printLongLine();
    }

    public void greetingMessage() {
        printMessage("Hello! I'm Duke\n\tWhat can I do for you?");
    }

    public void goodbyeMessage() {
        printMessage("Bye. Hope to see you again soon!");
    }

    public void addedTaskMessage(Task t, int size) {
        printLongLine();
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t" + t);
        System.out.println("\tNow you have " + size + " tasks in the list.");
        printLongLine();
    }

    public void deletedTaskMessage(Task t, int size) {
        printLongLine();
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t" + t);
        System.out.println("\tNow you have " + size + " tasks in the list");
        printLongLine();
    }

    public void markTaskAsDoneMessage(Task t) {
        printMessage("Nice! I've marked this task as done:\n\t" + t);
    }

    public void markTaskAsIncompleteMessage(Task t) {
        printMessage("OK, I've marked this task as not done yet:\n\t" + t);

    }

    public void printList(ArrayList<Task> tasks) {
        printLongLine();
        for (int i = 0; i < tasks.size(); i++) {
            int taskNumber = i + 1;
            System.out.printf("\t%d. %s\n", taskNumber, tasks.get(i));
        }
        printLongLine();
    }
}
