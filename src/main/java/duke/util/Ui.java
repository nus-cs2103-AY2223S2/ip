package duke.util;

import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Task;


/**
 * Interacts with the user by displaying messages.
 */
public class Ui {
    private static final String WELCOME_MESSAGE = "Welcome to Duke!\nHow can I help you?";
    private static final String GOODBYE_MESSAGE = "Exiting Duke now...\n" + "Bye, see you again!";

    private boolean isRunning = true;
    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Takes in user input and splits it by the first whitespace.
     * @return A String array containing the user input.
     */
    public String[] readUserInput() {
        String userInput = this.sc.nextLine();
        return userInput.split(" ", 2);
    }

    /**
     * Prints the specified message for the user.
     * @param message Message to be printed.
     */
    public void displayMessage(String message) {
        System.out.println(message);
    }

    /**
     * Prints the exit message for the user.
     */
    public String displayExitMessage() {
        displayMessage(GOODBYE_MESSAGE);
        return GOODBYE_MESSAGE;
    }

    /**
     * Prints the welcome message for the user.
     */
    public void displayWelcomeMessage() {
        displayMessage(WELCOME_MESSAGE);
    }

    /**
     * Lets the user know the current number of tasks.
     * @param taskList Contains the list of current tasks.
     */
    public String displayTasks(TaskList taskList) {
        String toDisplay;
        if (taskList.getSize() == 0) {
            toDisplay = "You currently have no tasks!";
        } else {
            toDisplay = "Here are the tasks in your list:";
            for (int i = 0; i < taskList.getSize(); i++) {
                toDisplay += String.format("\n%s. %s", i + 1, taskList.getTask(i));
            }
        }
        displayMessage(toDisplay);
        return toDisplay;
    }

    /**
     * Lets the user know what are the tasks that match.
     * @param matchedTasks An ArrayList containing matched tasks.
     */
    public String displayMatchedTasks(ArrayList<Task> matchedTasks) {
        String toDisplay;
        if (matchedTasks.size() == 0) {
            toDisplay = "No tasks found!";
        } else {
            toDisplay = "Here are the matching tasks in your list:";
            for (int i = 0; i < matchedTasks.size(); i++) {
                toDisplay += String.format("\n%s. %s", i + 1, matchedTasks.get(i));
            }
        }
        displayMessage(toDisplay);
        return toDisplay;
    }

    /**
     * Informs the user when a task has been added.
     * @param newTask Task that is added.
     * @param tasks The list that stores tasks.
     */
    public void displayAddedTask(Task newTask, TaskList tasks) {
        String toDisplay = String.format("Gotcha! I have added this task:\n%s\nNow you have %d tasks in the list.",
                newTask.toString(), tasks.getSize());
        displayMessage(toDisplay);
    }

    /**
     * Informs the user when a task has been deleted.
     * @param taskToDelete Task that needs to be deleted.
     * @param tasks The list that stores tasks.
     */
    public void displayDeletedTask(Task taskToDelete, TaskList tasks) {
        String toDisplay = String.format("Alright, I have removed this task:\n%s\nNow you have %d tasks in the list.",
                taskToDelete.toString(), tasks.getSize());
        displayMessage(toDisplay);
    }

    /**
     * Informs the user that a task is marked as done.
     * @param taskList The list that stores tasks.
     * @param index Index of the task in the TaskList.
     */
    public void displayMarkedTask(TaskList taskList, int index) {
        displayMessage("Ok, I've marked this task as done:\n" + taskList.getTask(index).toString());
    }

    public boolean isRunning() {
        return this.isRunning;
    }

    public void exit() {
        this.isRunning = false;
    }
}
