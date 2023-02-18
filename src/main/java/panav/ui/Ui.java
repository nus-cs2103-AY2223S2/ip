package panav.ui;

import java.util.Scanner;

import panav.task.Task;
import panav.task.TaskList;

/**
 * Class to represent all the UI tasks to interact with the user.
 */
public class Ui {

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
    public String showWelcome() {
        String text = "";

        text += "Hello hello! I'm Panav \n";
        text += "What's up bro";
        return text;
    }

    /**
     * Method to print the message once a task is added to the list.
     * @param tasks the list of tasks.
     * @param task The task which is added.
     */
    public String showAddTaskMessage(TaskList tasks, Task task) {
        String text = "";
        text += "Got it. I've added this task:\n";

        text += task.toString() + "\n";

        text += "Now you have " + tasks.getLength() + " tasks in the list.\n";
        return text;
    }
}
