package duke;


import java.util.ArrayList;
import java.util.Scanner;

import duke.tasks.Task;
import javafx.animation.PauseTransition;
import javafx.stage.Stage;
import javafx.util.Duration;


/**
 * Ui class deals with interactions with the user.
 */
public class Ui {
    private Scanner sc;
    private Stage stage;

    /**
     * Constructor for the Ui class.
     *
     * @param stage The Stage of the application.
     */
    public Ui(Stage stage) {
        this.stage = stage;
    }

    /**
     * Prints welcome message to user of Duke.
     */

    public static String welcomeMessage() {
        String message = "\tHello! I'm Duke\n"
                + "\tWhat can I do for you?\n";
        return message;
    }

    /**
     * Prints bye message to user of Duke and closes Duke.
     */
    public String byeMessage() {
        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished(event -> stage.close());
        delay.play();
        String message = "\t Bye. Hope to see you again soon!\n";
        return message;
    }

    /**
     * Outputs mark message to user.
     *
     * @param task Task to be marked Done.
     */
    public String showMark(Task task) {
        String message = "\tNice! I've marked this task as done:\n\t  " + task.toString();
        return message;
    }

    /**
     * Outputs unmark message to user.
     *
     * @param task Task to be marked Undone.
     */
    public String showUnmark(Task task) {
        String message = "\tOK, I've marked this task as not done yet:\n\t  " + task.toString();
        return message;
    }

    /**
     * Outputs add message to user.
     *
     * @param task Task to be added.
     * @param size Size of the tasklist
     */
    public String showAdd(Task task, int size) {
        String message = "\tGot it. I've added this task:\n\t  "
                + task.toString() + "\n\tNow you have "
                + size + " task(s) in the list.";
        return message;
    }

    /**
     * Outputs deleted message to user.
     *
     * @param task Task to be deleted.
     * @param size Size of the tasklist.
     */
    public String showDelete(Task task, int size) {
        String message = "\tNoted. I've removed this task:\n\t  "
                + task.toString() + "\n\tNow you have "
                + size + " task(s) in the list.";
        return message;
    }
    /**
     * Outputs deleted message to user.
     *
     * @param task Task to be deleted from findList.
     */
    public String showFindDelete(Task task) {
        String message = "\tNoted. I've removed this task:\n\t  "
                + task.toString();
        return message;
    }
    /**
     * Outputs the taskList to user.
     *
     * @param taskList TaskList which contains the list of tasks.
     */
    public String showList(TaskList taskList) {
        if (taskList.getSize() == 0) {
            String message = "\tThere is nothing in ur list currently";
            return message;
        } else {
            String message = taskList.printList();
            return message;
        }
    }

    /**
     * Prints the list of tasks found using the FindCommand.
     *
     * @param listOfTasksFound ArrayList consisting of strings of tasks found using FindCommand.
     */
    public String printFindList(ArrayList<Task> listOfTasksFound) {
        int i = 1;
        String s = "";
        s = "\tHere are the matching tasks in your list:";
        for (Task task : listOfTasksFound) {
            s += "\n\t"
                    + i
                    + ". "
                    + task.toString();
            i++;
        }
        s += "\n\tThere are "
                + listOfTasksFound.size()
                + " matching task(s) in your list.";
        return s;
    }
}
