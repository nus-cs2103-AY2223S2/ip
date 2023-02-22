package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Prints messages to the user during execution.
 */
public class Ui {

    /**
     * Prints error message for loading error.
     */
    public String showLoadingError() {
        return "Loading error: No saved task list found.";
    }

    public String showTaskNoError() {
        return "Please enter the task number.";
    }

    /**
     * Prints error messages for general error.
     * Exception: no message will be printed for error of "Failed Command Generation".
     * @param msg Given error massage.
     */
    public String showError(String msg) {
        if (!msg.equals("Failed Command Generation")) {
            return msg;
        }
        return "";
    }

    /**
     * Prints messages to show a new Todo task is added.
     * @param t New Todo task.
     */
    public String addTodo(Todo t) {
        String out = "This todo has been added!\n"
                + "  " + t;
        return out;
    }

    /**
     * Prints messages to show a new Deadline task is added.
     * @param d New Deadline task.
     */
    public String addDeadline(Deadline d) {
        String out = "This deadline had been added! Try to finish it early 0v0\n"
                + "  " + d;
        return out;
    }

    /**
     * Prints messages to show a new Event task is added.
     * @param e New Event task.
     */
    public String addEvent(Event e) {
        String out = "This event has been added! Hope you will enjoy it :D\n"
                + "  " + e;
        return out;
    }

    /**
     * Prints the total number of tasks in current task list.
     * @param tasks Current task list.
     */
    public String showCurrentTaskNo(TaskList tasks) {
        String out = "Now you have " + tasks.size() + " tasks in the list";
        return out;
    }

    /**
     * Prints the response message for Find command.
     */
    public String showSearchInformation() {
        return "Here are the matching tasks in your list: ";
    }

    public String showZeroSearchResult() {
        return "  I cannot find any relevant tasks in the list QvQ";
    }

    /**
     * Prints the target task.
     * @param task Target task.
     * */
    public String showTask(Task task, int index) {
        String out = index + "." + task.toString();
        return out;
    }

    /**
     * Prints the current task list.
     * @param tasks Current task list.
     */
    public String showList(TaskList tasks) {
        if (tasks.size() == 0) {
            return "The task list is empty.";
        } else {
            String out = "Here are the current tasks:\n";
            for (int i = 0; i < tasks.size(); i++) {
                out = out + (i + 1) + "." + tasks.get(i).toString() + "\n";
            }
            return out;
        }
    }

    /**
     * Print messages to alert user when input task index exceeds the length of task list.
     * @param index Input task index.
     */
    public String showIdExceedsList(int index) {
        return "I cannot find task " + (index) + " as it exceeds the total tasks number";
    }

    /**
     * Print messages to show that the target task has been marked done.
     * @param tasks Current task list.
     * @param index Input task index.
     */
    public String markTask(TaskList tasks, int index) {
        String out = "Nice! Great job for completing this task:\n"
                + (tasks.get(index).toString());
        return out;
    }

    /**
     * Print messages to show that the target task have been marked as not done.
     * @param tasks Current task list.
     * @param index Input task index.
     */
    public String unmarkTask(TaskList tasks, int index) {
        String out = "This item is marked as not done yet\n"
                + tasks.get(index).toString();
        return out;
    }

    /**
     * Prints messages to show that the target task have been deleted.
     * @param tasks Current task list.
     * @param index Input task index.
     */
    public String deleteTask(TaskList tasks, int index) {
        String out = "This task is deleted from the list:\n"
                + "  " + (tasks.get(index).toString()) + "\n"
                + "Now you have " + (tasks.size() - 1) + " tasks in the list";
        return out;
    }

    /**
     * Prints alert to user when a new Todo task is not created due to incorrect input format.
     */
    public String todoFormatAlert() {
        String out = "Adding new todo failed\n"
                + "The task name cannot be empty";
        return out;
    }

    /**
     * Prints alert to user when a new Deadline task is not created due to incorrect input format.
     */
    public String deadlineFormatAlert() {
        String out = "Adding new deadline failed\n"
                + "Please enter the deadline with format [name /ddmmyyyy time]";
        return out;
    }

    /**
     * Prints alert to user when a new Event task is not created due to incorrect input format.
     */
    public String eventFormatAlert() {
        String out = "Adding new event failed\n"
                + "Please enter the task with the format [name /ddMMyyyy HHmm /ddMMyyyy HMmm]";
        return out;
    }
}
