package duke.ui;

import java.util.ArrayList;

import duke.task.Task;

/**
 * Represents the user interface class that handles all message construction and printing.
 */
public class Ui {

    /**
     * Prints message signifying empty inputs
     * @return A message string.
     */
    public String print_empty_msg() {
        return "Nothing to say about this!!";
    }

    /**
     * Prints greeting message.
     * @return A message string.
     */
    public String print_greet_msg() {
        return "Hello! I'm Duke\n"
                + "     What can I do for you?";
    }

    /**
     * Prints bye message.
     * @return A message string.
     */
    public String print_bye_msg() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints the list of tasks.
     * @param tasks The list of tasks.
     * @return A message string.
     */
    public String print_task_list(ArrayList<Task> tasks) {
        String output = "";
        if (tasks.isEmpty()) {
            output = "None yet.";
        } else {
            int i = 1;
            for (Task t : tasks) {
                output += i + "." + t.toString() + "\n";
                i++;
            }
        }
        return output;
    }

    /**
     * Prints mark-as-done message.
     * @param task The task to mark as done.
     * @return A message string.
     */
    public String print_done_msg(Task task) {
        String output = "";
        if (task.isNull()) {
            output = "Unsuccessfully marked.";
            return output;
        }
        output = "Nice! I've marked this task as done: \n\t" + task;
        return output;
    }

    /**
     * Prints mark-as-not-done message.
     * @param task The task to mark as not done.
     * @return A message string.
     */
    public String print_undone_msg(Task task) {
        String output = "";
        if (task.isNull()) {
            output = "Unsuccessfully marked.";
            return output;
        }
        output = "OK, I've marked this task as not done yet: \n\t"
                + task;
        return output;
    }

    /**
     * Prints adding task status message.
     * @param task The task to add.
     * @return A message string.
     */
    public String print_add_msg(Task task, int size) {
        String output = "";
        if (task.isNull()) {
            output = "Unsuccessfully added.";
            return output;
        }
        output = "Got it. I've added this task: \n\t"
                + task
                + "\nNow you have "
                + size
                + " tasks in the list.";
        return output;
    }

    /**
     * Prints removing task status message.
     * @param task The task to remove.
     * @return A message string.
     */
    public String print_remove_msg(Task task, int size) {
        String output = "";
        if (task.isNull()) {
            output = "Unsuccessfully removed.";
            return output;
        }
        output = "Noted. I've removed this task: \n\t"
                + task
                + "\nNow you have " + size + " tasks in the list.";
        return output;
    }
}
