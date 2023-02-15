package ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import exceptions.TaskGenieException;
import tasks.Task;

/**
 * This class handles the user interface of TaskGenie.
 */
public class Ui {
    protected static final String OUTLINES = "____________________________________________________________";
    protected static final String INTRODUCTION = "Hello! I'm TaskGenie\nWhat can I do for you?";
    protected static final String READ_LIST = "Here are the tasks in your list:";
    protected static final String FAREWELL = "Bye. Hope to see you again soon!";
    protected static final String DONE_TASK = "Nice! I've marked this task as done:";
    protected static final String UNDONE_TASK = "Nice! I've marked this task as not done yet:";
    protected static final String DELETED_TASK = "Noted. I've removed this task:";
    protected static final String ADDED_TASK = "Got it. I've added this task:";
    protected static final String UPDATE_TASK = "Got it. I've updated this task:";

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private String output;

    /**
     * Constructor for the UI.
     */
    public Ui() {
        System.out.println(OUTLINES + "\n" + INTRODUCTION + "\n" + OUTLINES);
    }

    /**
     * Read and returns the user input.
     * @return User input.
     * @throws IOException Throws if there is an I/O error.
     */
    public String readCommand() throws IOException {
        return br.readLine();
    }

    /**
     * Returns the introduction.
     * @return Introduction.
     */
    public static String getIntroduction() {
        return INTRODUCTION;
    }

    /**
     * Print the outlines.
     * @return Outlines.
     */
    public String showLine() {
        System.out.println(OUTLINES);
        return OUTLINES;
    }

    /**
     * Print the starting message for listing of task.
     * @return The starting message for listing of task.
     */
    public String showList() {
        System.out.println(READ_LIST);
        return READ_LIST;
    }

    /**
     * Print the error message under a TaskGenieException.
     * @param e A TaskGenieException error.
     */
    public void showError(TaskGenieException e) {
        System.out.println(e.getMessage());
    }

    /**
     * Print the farewell message.
     * @return Farewell message.
     */
    public String showFarewell() {
        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(FAREWELL);
        return FAREWELL;
    }

    /**
     * Print the message for marking a task.
     * @param task Task that is marked.
     * @return Message for marking a task.
     */
    public String showMarkTask(Task task) {
        output = DONE_TASK + "\n" + task;
        System.out.println(output);
        return output;
    }

    /**
     * Print the message for unmarking a task.
     * @param task Task that is unmarked.
     * @return Message for unmarking a task.
     */
    public String showUnmarkTask(Task task) {
        output = UNDONE_TASK + "\n" + task;
        System.out.println(output);
        return output;
    }

    /**
     * Print the message for deleting a task.
     * @param task Task that is deleted.
     * @param size Number of tasks in the list.
     * @return Message for deleting a task.
     */
    public String showDeleteTask(Task task, int size) {
        output = DELETED_TASK + "\n" + task + "\n" + "Now you have " + size + " tasks in the list";
        System.out.println(output);
        return output;
    }

    /**
     * Print the message for adding a task.
     * @param task Task that is added.
     * @param size Number of task in the list.
     * @return Message for adding a task.
     */
    public String showAddTask(Task task, int size) {
        output = ADDED_TASK + "\n" + task + "\n" + "Now you have " + size + " tasks in the list";
        System.out.println(output);
        return output;
    }

    /**
     * Print all the tasks iteratively.
     * @param tasks The tasks.
     * @return String representation of all the tasks iteratively.
     */
    public String showAllTasks(List<Task> tasks) {
        output = showList();
        String str = "";
        for (int i = 1; i <= tasks.size(); i++) {
            str += i + "." + tasks.get(i - 1) + "\n";
        }
        System.out.print(str);
        return output + "\n" + str;
    }

    /**
     * Print the message for updating a task.
     * @param task Task that is updated.
     * @return Message for updating a task.
     */
    public String showUpdatedTask(Task task) {
        output = UPDATE_TASK + "\n" + task;
        System.out.println(output);
        return output;
    }
}
