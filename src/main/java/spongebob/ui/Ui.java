package spongebob.ui;

import spongebob.task.Task;
import spongebob.task.TaskList;

/**
 * User interface of the application.
 */
public class Ui {
    /**
     * Returns the welcome message upon start of the application.
     *
     * @return welcome message
     */
    public String showWelcomeMessage() {
        return "Hello! I'm Spongebob\n" + "What can I do for you?";
    }

    /**
     * Returns the exit message when application terminate.
     *
     * @return goodbye message.
     */
    public String showGoodbyeMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns response to the task command.
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
     * Returns response to the add task command.
     *
     * @param task task to be added into task list.
     * @param taskList list that contain the task.
     * @return message for add task command.
     */
    public String responseToLAddTaskCommand(Task task, TaskList taskList) {
        return "Got it. I've added this task:\n  " + task + "\nNow you have "
                + taskList.size() + " tasks in the list.";
    }


    /**
     * Returns response to the mark command
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
     * Returns response to the unmark response.
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
     * Returns response to delete command.
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
     * Returns response to find command.
     *
     * @param taskList list that contained the task
     * @return message for find task command.
     */
    public String responseToFindTaskCommand(TaskList taskList) {
        if (taskList.isEmpty()) {
            return "Could not find task matched!";
        }
        StringBuilder msg = new StringBuilder("Here are the matching tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            int index = i + 1;
            msg.append("\n").append(index).append(".").append(taskList.getTaskAt(i).toString());
        }
        return msg.toString();
    }

    /**
     * Returns the error caused in the program.
     *
     * @param errorMsg error message to print.
     * @return error message.
     */
    public String showError(String errorMsg) {
        return "OOPS!!! " + errorMsg;
    }
}
