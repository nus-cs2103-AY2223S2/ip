package duke.ui;

import duke.exception.DukeException;
import duke.task.Task;
import duke.tasklist.TaskList;

/**
 * Deals with interactions with the user
 */
public class Ui {
    /**
     * Sends greeting message to user
     */
    public String getGreetingsMessage() {
        return "Hello I'm duke.main.Duke\n" + "What can I do for you?";
    }

    /**
     * Sends goodbye message to user
     */
    public String getGoodByeMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Sends task details, formatted to user
     *
     * @param taskNumber
     * @param task
     */
    public String sendTaskDetails(int taskNumber, Task task) {
        return taskNumber + ". " + task + "\n";
    }

    /**
     * Loop through task lists to print task details
     *
     * @param tasks
     * @return task details
     * @throws DukeException
     */
    public String getTaskListDetailsMessage(TaskList tasks) throws DukeException {
        String s = "Here are the tasks in your list:\n";
        try {
            for (int i = 0; i < tasks.getSize(); i++) {
                s = s + this.sendTaskDetails(i + 1, tasks.getTask(i));
            }
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
        return s;
    }
    /**
     * Return task details based on keyword by user
     *
     * @param tasks
     * @return task details
     * @throws DukeException
     */
    public String getFindTaskListMessage(TaskList tasks) throws DukeException {
        String s = "Here are the matching tasks in your list: \n";
        try {
            for (int i = 0; i < tasks.getSize(); i++) {
                s = s + this.sendTaskDetails(i + 1, tasks.getTask(i));
            }
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
        return s;
    }

    /**
     * Return add task message
     *
     * @param task
     * @param taskListSize
     * @return
     */
    public String getAddTaskMessage(Task task, int taskListSize) {
        String s = "Got it. I've added this task:\n";
        s = s + task.toString() + "\n";
        s = s + this.getTotalTasksMessage(taskListSize);
        return s;
    }

    /**
     * Returns task list size message
     *
     * @param taskListSize
     * @return
     */
    public String getTotalTasksMessage(int taskListSize) {
        return "Now you have " + taskListSize + " tasks in the list.";
    }

    /**
     * Return remove task message
     *
     * @param task
     * @return
     */
    public String getRemoveTaskMessage(Task task) {
        String s = "Noted. I've removed this task:\n";
        s = s + task.toString() + "\n";
        return s;
    }

    /**
     * Return mark task message
     *
     * @param task
     * @return
     */
    public String getMarkTaskMessage(Task task) {
        String s = "Nice! I've marked this task as done: \n";
        s = s + task.toString();
        return s;
    }

    /**
     * Return unmark task message
     *
     * @param task
     * @return
     */
    public String getUnmarkTaskMessage(Task task) {
        String s = "OK, I've marked this task as not done yet: \n";
        s = s + task.toString();
        return s;
    }

    /**
     * Return unknown message
     *
     * @return
     */
    public String getUnknownMessage() {
        return "I'm sorry, but I don't know what that means :-(";
    }

    public String getStatsMessage(TaskList tasks) throws DukeException {
        String s = "Here are the stats\n";
        s = s + "Total number of tasks: " + tasks.getSize() + "\n";
        s = s + "Total number of marked tasks: " + tasks.getSizeOfMarkedTasks() + "\n";
        s = s + "Total number of unmarked tasks: " + tasks.getSizeOfUnmarkedTasks() + "\n";
        return s;
    }
}
