package ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import exceptions.DukeException;
import tasks.Task;

/**
 * This class handles the user interface of Duke.
 */
public class Ui {
    protected static final String LOGO = " ____        _        \n"
                                        + "|  _ \\ _   _| | _____ \n"
                                        + "| | | | | | | |/ / _ \\\n"
                                        + "| |_| | |_| |   <  __/\n"
                                        + "|____/ \\__,_|_|\\_\\___|\n";
    protected static final String OUTLINES = "____________________________________________________________";
    protected static final String INTRODUCTION = "Hello! I'm Duke\nWhat can I do for you?";
    protected static final String READ_LIST = "Here are the tasks in your list:";
    protected static final String FAREWELL = "Bye. Hope to see you again soon!";
    protected static final String DONE_TASK = "Nice! I've marked this task as done:";
    protected static final String UNDONE_TASK = "Nice! I've marked this task as not done yet:";
    protected static final String DELETED_TASK = "Noted. I've removed this task:";
    protected static final String ADDED_TASK = "Got it. I've added this task:";

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

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
     * Print the outlines.
     */
    public void showLine() {
        System.out.println(OUTLINES);
    }

    /**
     * Print the starting message for listing of task.
     */
    public void showList() {
        System.out.println(READ_LIST);
    }

    /**
     * Print the error message under a DukeException.
     * @param e A DukeException error.
     */
    public void showError(DukeException e) {
        System.out.println(e.getMessage());
    }

    /**
     * Print the farewell message.
     * @throws IOException Throws if there is an I/O error.
     */
    public void showFarewell() {
        System.out.println(FAREWELL);
        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Print the message for marking a task.
     * @param task Task that is marked.
     */
    public void showMarkTask(Task task) {
        System.out.println(DONE_TASK + "\n" + task);
    }

    /**
     * Print the message for unmarking a task.
     * @param task Task that is unmarked.
     */
    public void showUnmarkTask(Task task) {
        System.out.println(UNDONE_TASK + "\n" + task);
    }

    /**
     * Print the message for deleting a task.
     * @param task Task that is deleted.
     * @param size Number of tasks in the list.
     */
    public void showDeleteTask(Task task, int size) {
        System.out.println(DELETED_TASK + "\n" + task + "\n" + "Now you have " + size + " tasks in the list");
    }

    /**
     * Print the message for adding a task.
     * @param task Task that is added.
     * @param size Number of task in the list.
     */
    public void showAddTask(Task task, int size) {
        System.out.println(ADDED_TASK + "\n" + task + "\n" + "Now you have " + size + " tasks in the list");
    }

    /**
     * Print all the tasks iteratively.
     * @param tasks The tasks.
     */
    public void showAllTasks(List<Task> tasks) {
        String str = "";
        for (int i = 1; i <= tasks.size(); i++) {
            str += i + "." + tasks.get(i - 1) + "\n";
        }
        System.out.print(str);
    }
}
