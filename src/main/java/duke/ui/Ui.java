package duke.ui;

import duke.tasklist.TaskList;
import duke.task.Task;

import java.util.Scanner;

public class Ui {

    private final Scanner scanner;

    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public Ui() {

        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {

        if (this.scanner.hasNextLine()) {
            String userInput = this.scanner.nextLine();
            makeSeperation();
            return userInput;
        }
        return null;
    }

    public void showInitMessage() {

        System.out.println("Hello from\n" + Ui.LOGO);
    }

    /**
     * Prints out the greeting message and a line separation.
     */
    public void showGreeting() {
        System.out.println("\tHello! I'm Duke\n" +
                "\tWhat can I do for you?");
        makeSeperation();
    }

    public void showMarkedTask(String taskDescription) {
        System.out.println("\tNice! I've marked this task as done:\n\t  "
                + taskDescription);
    }

    public void showUnmarkedTask(String taskDescription) {
        System.out.println("\tOK, I've marked this task as not done yet:\n\t  "
                + taskDescription);
    }

    public void showAddedTask(Task t, TaskList tl) {
        System.out.println("\tGot it. I've added this task:\n\t  " + t
                + "\n\t" + String.format("Now you have %d tasks in the list."
                , tl.getLength()));
    }

    public void showDeletedTask(Task t, TaskList tl) {
        System.out.println("\tNoted. I've removed this task:\n\t  " +
                t.toString() + String.format("\n\tNow you have %d tasks in the list."
                , tl.getLength()));
    }

    public void showByeMessage() {

        System.out.println("\tBye. Hope to see you again soon!");
    }

    public void showError(String e) {
        System.out.println(e);
        return;
    }

    /**
     * Prints out a line separation.
     */
    public void makeSeperation() {
        System.out.println("\t____________________________________________________________");
    }

    public void showLoadingError() {
        System.out.println("Error loading file from storage.");
        makeSeperation();
    }
}
