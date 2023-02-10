package duke;

import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;


/**
 * Class contains variables and methods related to interactions with the user.
 */
public class Ui {
    private static final String LINE = "____________________________________________________________";
    private final Scanner input;

    /**
     * Creates an instance of Ui.
     */
    public Ui() {
        this.input = new Scanner(System.in);
    }

    /**
     * Reads user input.
     * @return user input as a String.
     */
    public String readCommand() {
        return input.nextLine();
    }

    /**
     * Prints a line.
     */
    public void showLine() {
        System.out.println(LINE);
    }

    /**
     * Prints welcome message.
     */
    public void showWelcome() {
        showLine();
        System.out.println("Hello! I'm Kirby!\n" + "What can I do for you? :)");
        showLine();
    }

    public void showLoadingError() {
        showLine();
        System.out.println("Sorry I wasn't able to load the file");
        showLine();
    }

    /**
     * Prints error message from an exception.
     * @param errorMsg String containing error message of exception.
     */
    public void showError(String errorMsg) {
        System.out.println(errorMsg);
    }

    /**
     * Prints exit message.
     */
    public void showExit() {
        showLine();
        System.out.println("Bye bye! Hope to see you again soon!! :>");
        showLine();
    }

    /**
     * Prints TaskList of Duke.
     * @param lst TaskList to be printed.
     */
    public void showList(TaskList lst) {
        showLine();
        lst.printList();
        showLine();
    }

    /**
     * Prints command message after mark command is executed successfully.
     * @param task task marked as done.
     */
    public void showMarkedTask(Task task) {
        showLine();
        System.out.println("Okay! I've marked this task as done:");
        System.out.println(task);
        showLine();
    }

    /**
     * Prints command message after unmark command is executed successfully.
     * @param task task marked as unDone.
     */
    public void showUnmarkedTask(Task task) {
        showLine();
        System.out.println("Okay! I've marked this task as not done yet:");
        System.out.println(task);
        showLine();
    }

    /**
     * Prints command message after delete command is successfully executed.
     * @param task task deleted.
     * @param size current size of the list.
     */
    public void showDeletedTask(Task task, int size) {
        showLine();
        System.out.println("Okay! I've removed this task from the list:");
        System.out.println(task);
        System.out.println(String.format("Now you have %d tasks in the list!", size));
        showLine();
    }

    /**
     * Prints command message after todoCommand is successfully executed.
     * @param todo Todo task created.
     * @param size current size of the list.
     */
    public void showTodo(Todo todo, int size) {
        showLine();
        System.out.println("Got it! I've added: ");
        System.out.println(" " + todo);
        System.out.println(String.format("Now you have %d tasks in the list!", size));
        showLine();
    }

    /**
     * Prints command message after deadline command is successfully executed.
     * @param deadline Deadline task created.
     * @param size current size of the list.
     */
    public void showDeadline(Deadline deadline, int size) {
        showLine();
        System.out.println("Got it! I've added: ");
        System.out.println(" " + deadline);
        System.out.println(String.format("Now you have %d tasks in the list!", size));
        showLine();
    }

    /**
     * Prints command message after event command is successfully executed.
     * @param event Event task created.
     * @param size current size of the list.
     */
    public void showEvent(Event event, int size) {
        showLine();
        System.out.println("Got it! I've added: ");
        System.out.println(" " + event);
        System.out.println(String.format("Now you have %d tasks in the list!", size));
        showLine();
    }
}
