package duke.ui;

import duke.task.Task;
import duke.task.TaskList;

/**
 * User interface of the application.
 */
public class Ui {
    /**
     * Return the welcome message upon start of the application.
     *
     * @return welcome message
     */
    public String showWelcomeMessage() {
        return  "Hello! I'm Duke\n" + "What can I do for you?";
    }

    /**
     * Return the exit message when application terminate.
     *
     * @return goodbye message.
     */
    public String showGoodbyeMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Return response to the task command.
     *
     * @param taskList list that contain the task.
     * @return message for list command.
     */
    public String responseToListCommand(TaskList taskList) {
        StringBuilder msg = new StringBuilder("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            int tmp = i + 1;
            msg.append("\n").append(tmp).append(".").append(taskList.getTaskAt(i));
        }
        return msg.toString();
    }


    /**
     * Return response to the add task command.
     *
     * @param task task to be added into task list.
     * @param taskList list that contain the task.
     * @return message for add task command.
     */
    public String responseToLAddTaskCommand(Task task, TaskList taskList) {
        return"Got it. I've added this task:\n  " + task + "\nNow you have "
                + taskList.size() + " tasks in the list.";
    }


    /**
     * Return response to the mark command
     *
     * @param task task to be marked as done.
     * @return message for mark command.
     */
    public String responseToMarkTaskCommand(Task task) {
        String msg = "Nice! I've marked this task as done:\n";
        msg += "  " + task;
        return msg;
    }

    /**
     * Return response to the unmark response.
     *
     * @param task task to be marked as undone.
     * @return message for unmark command.
     */
    public String responseToUnmarkTaskCommand(Task task) {
        String msg = "OK, I've marked this task as not done yet:\n";
        msg += "  " + task;
        return msg;
    }

    /**
     * Return response to delete command.
     *
     * @param taskList list that contained the task
     * @param index task to be deleted from task list.
     * @return message for delete command.
     */
    public String responseToDeleteTaskCommand(TaskList taskList, int index) {
        return "Noted. I've removed this task:\n  " + taskList.getTaskAt(index)
                + "\nNow you have " + (taskList.size() - 1) + " tasks in the list.";
    }

    /**
     * Return response to delete command.
     *
     * @param taskList list that contained the task
     * @return message for find task command.
     */
    public String responseToFindTaskCommand(TaskList taskList) {
        if (taskList.isEmpty()) {
            return "Could not find task matched!";
        }

        StringBuilder msg = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            int index = i + 1;
            msg.append("\n").append(index).append(".").append(taskList.getTaskAt(i).toString());
        }
        return msg.toString();
    }

    /**
     * Return the error caused in the program.
     *
     * @param errorMsg error message to print.
     * @return error message.
     */
    public String showError(String errorMsg) {
        return "OOPS!!! " + errorMsg;
    }
}
