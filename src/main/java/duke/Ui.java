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
    public String showLine() {
        return LINE + "\n";
    }

    /**
     * Prints welcome message.
     */
    public String showWelcome() {
        return showLine() + "Hello! I'm Luffy!\n" + "What can I do for you? :)" + "\n" + showLine();
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
    public String showError(String errorMsg) {
        return errorMsg + "\n";
    }

    /**
     * Prints exit message.
     */
    public String showExit() {
        return showLine() + "Bye bye! Luffy will go to sleep now!\nHope to see you again soon!! :>\n" + showLine();
    }

    /**
     * Prints TaskList of Duke.
     * @param lst TaskList to be printed.
     */
    public String showList(TaskList lst) {
        return showLine() + lst.printList() + showLine();
    }

    /**
     * Prints command message after mark command is executed successfully.
     * @param task task marked as done.
     */
    public String showMarkedTask(Task task) {
        return showLine() + "Okay! I've marked this task as done:\n" + task.toString() + "\n" + showLine();
//        showLine();
//        System.out.println("Okay! I've marked this task as done:");
//        System.out.println(task);
//        showLine();
    }

    /**
     * Prints command message after unmark command is executed successfully.
     * @param task task marked as unDone.
     */
    public String showUnmarkedTask(Task task) {
        return showLine() + "Okay! I've marked this task as not done yet:\n"
                + task.toString() + "\n" + showLine();
//        showLine();
//        System.out.println("Okay! I've marked this task as not done yet:");
//        System.out.println(task);
//        showLine();
    }

    /**
     * Prints command message after delete command is successfully executed.
     * @param task task deleted.
     * @param size current size of the list.
     */
    public String showDeletedTask(Task task, int size) {
        return showLine() + "Okay! I've removed this task from the list:\n" + task.toString() + "\n"
                + String.format("Now you have %d tasks in the list!\n", size) + showLine();
//        showLine();
//        System.out.println("Okay! I've removed this task from the list:");
//        System.out.println(task);
//        System.out.println(String.format("Now you have %d tasks in the list!", size));
//        showLine();
    }

    /**
     * Prints command message after todoCommand is successfully executed.
     * @param todo Todo task created.
     * @param size current size of the list.
     */
    public String showTodo(Todo todo, int size) {
        return showLine() + "Got it! I've added: \n" + " " + todo.toString() + "\n"
                + String.format("Now you have %d tasks in the list!\n", size) + showLine();
//        showLine();
//        System.out.println("Got it! I've added: ");
//        System.out.println(" " + todo);
//        System.out.println(String.format("Now you have %d tasks in the list!", size));
//        showLine();
    }

    /**
     * Prints command message after deadline command is successfully executed.
     * @param deadline Deadline task created.
     * @param size current size of the list.
     */
    public String showDeadline(Deadline deadline, int size) {
        return showLine() + "Got it! I've added: \n" + " " + deadline.toString() + "\n"
                + String.format("Now you have %d tasks in the list!\n", size) + showLine();
//        showLine();
//        System.out.println("Got it! I've added: ");
//        System.out.println(" " + deadline);
//        System.out.println(String.format("Now you have %d tasks in the list!", size));
//        showLine();
    }

    /**
     * Prints command message after event command is successfully executed.
     * @param event Event task created.
     * @param size current size of the list.
     */
    public String showEvent(Event event, int size) {
        return showLine() + "Got it! I've added: \n" + " " + event.toString() + "\n"
                + String.format("Now you have %d tasks in the list!\n", size) + showLine();
//        showLine();
//        System.out.println("Got it! I've added: ");
//        System.out.println(" " + event);
//        System.out.println(String.format("Now you have %d tasks in the list!", size));
//        showLine();
    }
}
