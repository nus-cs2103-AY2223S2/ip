package duke.ui;

import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Task;
import duke.tasklist.TaskList;

/**
 * This class encapsulates everything <code>Duke</code> Duke's interactions with the user.
 *
 * @author Bo Kuan (LG17)
 * @version CS2103T AY22/23 Semester 2
 */
public class Ui {
    /**
     * <code>Duke</code>'s logo.
     */
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    /**
     * <code>Scanner</code> to take user input.
     */
    private final Scanner scanner;

    /**
     * Constructor for <code>Duke</code>'s <code>Ui</code>.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads from <code>System.in</code> and returns input in <code>String</code> format.
     * @return Input in <code>String</code> format.
     */
    public String readCommand() {

        if (this.scanner.hasNextLine()) {
            String userInput = this.scanner.nextLine();
            makeSeperation();
            return userInput;
        }
        return null;
    }
    /**
     * Prints <code>Duke</code>'s init message.
     */
    public void showInitMessage() {

        System.out.println("Hello from\n" + Ui.LOGO);
    }
    /**
     * Prints out the greeting message and a line separation.
     */
    public void showGreeting() {
        System.out.println("\tHello! I'm Duke\n"
                + "\tWhat can I do for you?");
        makeSeperation();
    }
    /**
     * Prints <code>Duke</code>'s marked-task message.
     * @return
     */
    public String showMarkedTask(String taskDescription) {
        System.out.println("\tNice! I've marked this task as done:\n\t  "
                + taskDescription);
        return "\tNice! I've marked this task as done:\n\t  "
                + taskDescription;
    }
    /**
     * Prints <code>Duke</code>'s unmarked-task message.
     */
    public String showUnmarkedTask(String taskDescription) {
        System.out.println("\tOK, I've marked this task as not done yet:\n\t  "
                + taskDescription);
        return "\tOK, I've marked this task as not done yet:\n\t  "
                + taskDescription;
    }
    /**
     * Prints <code>Duke</code>'s added-task message.
     */
    public String showAddedTask(Task t, TaskList tl) {
//        System.out.println("\tGot it. I've added this task:\n\t  " + t
//                + "\n\t" + String.format("Now you have %d tasks in the list.",
//                tl.getLength()));
        return "\tGot it. I've added this task:\n\t  " + t
                + "\n\t" + String.format("Now you have %d tasks in the list.",
                tl.getLength());
    }
    /**
     * Prints <code>Duke</code>'s deleted-task message.
     */
    public String showDeletedTask(Task t, TaskList tl) {
        System.out.println("\tNoted. I've removed this task:\n\t  "
                + t.toString() + String.format("\n\tNow you have %d tasks in the list.",
                tl.getLength()));
        return "\tNoted. I've removed this task:\n\t  "
                + t.toString() + String.format("\n\tNow you have %d tasks in the list.",
                tl.getLength());
    }
    /**
     * Prints <code>Duke</code>'s cleared-tasks message.
     */
    public String showClearTasksMessage() {
        System.out.println("\tI have cleared the task list.");
        return "\tI have cleared the task list.";
    }
    /**
     * Prints <code>Duke</code>'s goodbye message.
     */
    public String showByeMessage() {
        System.out.println("\tBye. Hope to see you again soon!");
        return "\tBye. Hope to see you again soon!";
    }
    public String showTaskListPretty(ArrayList<Task> tasks) {
        String toPrint = "";
        int i = 1;
        for (Task t : tasks) {
            toPrint += "\t" + (i) + ". " + t.toString() + "\n";
            i++;
        }
        System.out.println((toPrint));
        return toPrint;
    }

    /**
     * Prints <code>Duke</code>'s error response message.
     */
    public String showError(String e) {
        System.out.println(e);
        return e;
    }

    /**
     * Prints out a line separation.
     */
    public void makeSeperation() {
        System.out.println("\t____________________________________________________________");
    }
    /**
     * Prints <code>Duke</code>'s loading-error-from-tasks.txt message.
     */
    public void showLoadingError() {
        System.out.println("Error loading file from storage.");
        makeSeperation();
    }
}
