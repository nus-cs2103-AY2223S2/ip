package duke.ui;

import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Task;
import duke.tasklist.TaskList;

/**
 * Ui class that handles all user interactions.
 */
public class Ui {

    private Scanner sc = new Scanner(System.in);

    /**
     * Prints a horizontal line.
     */
    public void showLine() {
        System.out.println("  ------------------------------------");
    }

    /**
     * Prints the welcome message.
     */
    public void showWelcome() {
        showLine();
        System.out.println("  Hello! I'm Duke\n  What can I do for you?");
        showLine();
    }

    /**
     * Prints the goodbye message.
     */
    public void showBye() {
        System.out.println("  Bye. Hope to see you again soon!");
    }

    
    /** 
     * Takes in a file path and prints an attempt to load message to the user.
     * 
     * @param filePath The file path of the data file.
     */
    public void showLoading(String filePath) {
        System.out.println(String.format("%n  Trying to load tasks from %s...", filePath));
    }

    
    /** 
     * Prints a successful load message to the user if tasks is not empty.
     * 
     * @param tasks The current task list.
     */
    public void showSuccessfulLoad(TaskList tasks) {
        if (tasks.size() == 0) {
            System.out.println("  Data file was loaded but no tasks could be found.");
        } else {
            System.out.println("  Tasks loaded successfully!");
        }
    }

    
    /** 
     * Prints the error message in the default way.
     * 
     * @param message The error message to be printed.
     */
    public void showError(String message) {
        System.out.println(String.format("  %s", message));
    }

    /**
     * Prints the loading error message when the data file could not be found.
     */
    public void showLoadingError() {
        System.out.println(
                "\n  Data file could not be found.\n  A new data file will be auto generated upon insert of a task.");
    }

    
    /** 
     * Returns a Command upon receiving user input.
     * 
     * @return String The user input.
     */
    public String readCommand() {
        return this.sc.nextLine().trim();
    }

    /**
     * Closes the scanner after bye command has been read.
     */
    public void closeScanner() {
        this.sc.close();
    }

    
    /** 
     * Prints task when it is added or removed.
     * 
     * @param task The task to be printed.
     * @param taskList The current task list.
     * @param toAdd True if task is to be added, false otherwise.
     */
    public void printTask(Task task, ArrayList<Task> taskList, boolean toAdd) {
        if (toAdd) {
            System.out.println("  Got it. I've added this task:");
        } else {
            System.out.println("  Noted. I've removed this task:");
        }
        System.out.println(String.format("  %s", task.toString()));
        System.out.println(String.format("  Now you have %d %s in the list.", taskList.size(),
                taskList.size() == 1 ? "task" : "tasks"));
    }

    
    /** 
     * Prints task when it is marked as done or not done.
     * 
     * @param task The task to be printed.
     * @param toMark True if task is to be marked as done, false otherwise.
     */
    public void printMarkTask(Task task, boolean toMark) {
        if (toMark) {
            System.out.println("  Nice! I've marked this task as done:");
        } else {
            System.out.println("  I've marked this task as not done:");
        }
        System.out.println(String.format("  %s", task.toString()));
    }

    /**
     * Prints the message when the task list is empty.
     */
    public void printNoTasks() {
        System.out.println("  No tasks added yet");
    }

    
    /** 
     * Prints the tasks in the task list.
     * 
     * @param tasks The current task list.
     */
    public void printTasks(TaskList tasks) {
        System.out.println("  Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(String.format("  %d.%s", i + 1, tasks.getTask(i).toString()));
        }
    }

}
