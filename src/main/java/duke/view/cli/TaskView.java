package duke.view.cli;

import java.util.List;
import java.util.Scanner;

import duke.interfaces.View;
import duke.model.Task;

/**
 * Class that handles displaying task model information to the user, and receiving user input.
 */
public class TaskView implements View {
    private final Scanner sc;
    private List<Task> displayedTaskList;
    public TaskView() {
        this.sc = new Scanner(System.in);
    }
    /**
     * Get the task at the supplied index from the list of displayed tasks.
     * @param index The index of the task.
     * @return the task at the supplied index from the list of displayed tasks.
     */
    public Task getDisplayedTask(int index) {
        return displayedTaskList.get(index);
    }
    public int getNumDisplayedTasks() {
        return displayedTaskList.size();
    }

    /**
     * Display a message to the user.
     * @param string The message to display.
     */
    @Override
    public void showMessage(String string) {
        System.out.println("calling gui show message");
        System.out.println("____________________________________________________________");
        System.out.println(string);
        System.out.println("____________________________________________________________");
    }

    /**
     * Display an error to the user.
     * @param string the error message to show
     */
    @Override
    public void showError(String string) {
        System.err.println(string);
    }

    /**
     * A method to display the tasks supplied.
     * @param tasks the list of tasks to display
     */
    @Override
    public void setTasks(List<Task> tasks, boolean renderToScreen) {
        this.displayedTaskList = tasks;
        if (renderToScreen) {
            System.out.println("____________________________________________________________");
            System.out.println("Here are the tasks in your list:");
            int index = 1;
            for (Task task: tasks) {
                System.out.println(index++ + ". " + task.toString());
            }
            System.out.println("____________________________________________________________");
        }
    }

    /**
     * Get input from the user.
     * @return the next user input.
     */

    public String getUserInput() {
        return this.sc.nextLine();
    }
}
