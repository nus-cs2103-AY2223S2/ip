package duke.ui;

import duke.TaskList;
import duke.Task;

public class Ui {
    /**
     * Ui class to handle Duke's output
     */

    /**
     * Prints out all of Duke's tasks.
     * @param tasks list of tasks.
     */
    public String displayTaskList(TaskList tasks) {
        return tasks.toString();
    }

    /**
     * Prints out the appropriate message when adding task to the list
     * @param task task to be added
     * @param taskList list of task
     * @return Duke's response message
     */
    public String displayAddTaskMessage(Task task, TaskList taskList) {
        StringBuilder sb = new StringBuilder();
        sb.append("Got it. I've added this task:\n");
        sb.append(task.toString());
        sb.append("\nNow you have " + taskList.size() + " tasks in the list.\n");
        return sb.toString();
    }

    /**
     * Prints out the appropriate message when adding task to the list
     * @param task task to be added
     * @param taskList list of task
     * @return Duke's response message
     */
    public String displayDeleteTaskMessage(Task task, TaskList taskList) {
        StringBuilder sb = new StringBuilder();
        sb.append("Noted. I've removed this task:\n");
        sb.append(task.toString());
        sb.append("\nNow you have " + taskList.size() + " tasks in the list.\n");
        return sb.toString();
    }

    /**
     * Prints out the appropriate message when adding task to the list
     * @param task task to be added
     * @return Duke's response message
     */
    public String displayMarkMessage(Task task) {
        StringBuilder sb = new StringBuilder();
        sb.append("Nice! I've marked this task as done:\n");
        sb.append(task.toString());
        return sb.toString();
    }

    /**
     * Prints out the appropriate message when unmarking a task
     * @param task task to be added
     * @return Duke's response message
     */
    public String displayUnmarkedMessage(Task task) {
        StringBuilder sb = new StringBuilder();
        sb.append("OK, I've marked this task as not done yet:\n");
        sb.append(task.toString());
        return sb.toString();
    }

    /**
     * Prints out Duke's goodbye message
     * @return Duke's response message
     */
    public String displayGoodbyeMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints out the appropriate misc message
     * @return Duke's response message
     */
    public String displayMsg(String output) {
        return output;
    }

    public String displayUpdateMessage(Task task) {
        StringBuilder sb = new StringBuilder();
        sb.append("Task has been updated\n");
        sb.append(task.toString());
        return sb.toString();
    }
}
