package ui;

import storage.Storage;
import task.Task;
import tasklist.TaskList;

/**
 * User Interface Class that deals with interactions with the user.
 */
public class Ui {
    private static final String ENTRY_OUTPUT = "Hello! I'm Duke\nWhat can I do for you?";
    private static final String BYE_OUTPUT = " Bye. Hope to see you again soon!";
    private boolean isClosed;

    /**
     * Constructor.
     */
    public Ui() {
        this.isClosed = false;
    }

    /**
     * Closes the Ui.
     */
    public void close() {
        this.isClosed = true;
    }

    /**
     * Returns the Exception given.
     * @param e Exception.
     * @return Exception in a String type
     */
    public String printException(Exception e) {
        return e.toString();
    }

    /**
     * Returns the exit output.
     * @param list List of tasks.
     * @param storage Storage.
     * @return response
     */
    public String printBye(TaskList list, Storage storage) {
        storage.save(list);
        return BYE_OUTPUT;
    }

    /**
     * Returns the entry welcome.
     * @return response
     */
    public String printEntry() {
        return ENTRY_OUTPUT;
    }

    /**
     * Returns the response of deleting the task from the list.
     *
     * @param task Task that is deleted.
     * @param list List of tasks.
     * @return Response
     */
    public String printHandleDelete(Task task, TaskList list) {
        String output = "Noted. I've removed this task:\n";
        output += "  " + task.toString();
        output += String.format("\nNow you have %d tasks in the list.", list.size());
        return output;
    }

    /**
     * Returns the response of marking the task.
     *
     * @param task Task that is marked.
     * @return Response
     */
    public String printHandleMark(Task task) {
        return "Nice! I've marked this task as done:\n" + "  " + task.toString();
    }

    /**
     * Returns the response of unmarking the task.
     *
     * @param task Task that is unmarked.
     * @return Response
     */
    public String printHandleUnmark(Task task) {
        return "OK,, I've marked this task as not done yet:\n" + "  " + task.toString();
    }

    /**
     * Returns the response of adding the task to the list.
     *
     * @param newTask Task is added to the list
     * @param list List of tasks
     * @return Response of adding task to list
     */
    public String printAddTask(Task newTask, TaskList list) {
        return "Got it. I've added this task:\n" + "  " + newTask.toString()
                + String.format("\nNow you have %d tasks in the list.", list.size());
    }

    /**
     * Returns the tasks in the list.
     *
     * @param list List of tasks.
     * @return Tasks in the list
     */
    public String printGetList(TaskList list) {
        String output = "Here are the tasks in your list:\n";
        for (int i = 0; i < list.size(); i++) {
            Task task = list.getTask(i);
            output += "\n" + i + 1 + "." + task.toString();
        }
        return output;
    }

    /**
     * Returns the error if unable to load list from the hard disk.
     * @return response of error
     */
    public String showLoadingError() {
        return "Could not load list. Using a new, empty list instead";
    }

    /**
     * Returns the search output.
     *
     * @param list List of tasks.
     * @param searchWord Search word.
     * @return Search output
     */
    public String printFind(TaskList list, String searchWord) {
        String output = "Here are the matching tasks in your list:\n";
        for (Task task: list.getList()) {
            if (task.getDescription().contains(searchWord)) {
                output += "\n" + task.toString();
            }
        }
        return output;
    }

}
