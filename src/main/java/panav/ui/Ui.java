package panav.ui;

import java.util.Scanner;

import panav.task.Task;
import panav.task.TaskList;



/**
 * Class to represent all the UI tasks to interact with the user.
 */
public class Ui {

    private static final String LINE_DIVIDER = "____________________________________________________________";

    private Scanner sc = new Scanner(System.in);



    /**
     * Method to print the error while loading tasks from saved file.
     */
    public void showLoadingError() {
        System.out.println("Oops!! There was an error in loading text from your file");
        System.out.println("Make sure your file exists in the correct location");
    }

    /**
     * Method to print the welcome message when Panav is loaded up.
     */
    public void showWelcome() {
        showLine();
        System.out.println("Hello hello! I'm Panav");
        System.out.println("What's up bro");
        showLine();
    }

    /**
     * Method to print the line divider.
     */
    public void showLine() {
        System.out.println(LINE_DIVIDER);
    }

    /**
     * Method to read user's command.
     * @return the String representation of the command.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Method to print the message once a task is added to the list.
     * @param tasks the list of tasks.
     * @param task The task which is added.
     */
    public void showAddTaskMessage(TaskList tasks, Task task) {
        showLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + tasks.getLength() + " tasks in the list.");
        showLine();
    }
}
