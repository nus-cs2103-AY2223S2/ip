package command;

import task.Task;
import task.TaskList;

import java.util.Scanner;

public class Ui {
    // For all interactions with the user
    private Scanner userInput;
    private Boolean isOpenForInput;

    /**
     * Constructor for UI object.
     */
    public Ui() {
        userInput = new Scanner(System.in);
        isOpenForInput = true;
    }

    //@@author IceFire
    //Reused from https://stackoverflow.com/questions/36514289
    // with minor modifications
    /**
     * Prints a dashed line.
     */
    private void dashedLine()
    {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 90; i++) {
            sb.append("-");
        }
        System.out.println(sb.toString());
    }

    /**
     * Closes the UI from accepting any more inputs.
     */
    public void close() {
        dashedLine();
        System.out.println("Pleasure doing business with you.");
        isOpenForInput = false;
        dashedLine();
    }

    public boolean isOpenForInput() {
        return isOpenForInput;
    }

    /**
     * Prints the corresponding error message when the user inputs a command not registered
     *  in Duke.
     */
    public void unknownCommand() {
        dashedLine();
        System.out.println("Sorry sir, didn't quite get that.");
        dashedLine();
    }

    /**
     * Prints the opening message for Duke.
     */
    public void startMessage() {
        dashedLine();
        String intro = "My name is Skyler White yo \nHow can I help you?";
        System.out.println(intro);
        dashedLine();
    }

    public String getInput() {
        return userInput.nextLine();
    }

    /**
     * Displays an error message if Duke fails to read from the user's provided input path or the default write path
     * of the program after it terminates.
     */
    public void showLoadingError() {
        dashedLine();
        System.out.println("Looks like you don't have any old lists for me to include. That's alright; we'll start" +
                " from scratch!");
        dashedLine();
    }

    public void showArrayOutOfBoundsError() {
        dashedLine();
        System.out.println("Woah there. Got an index problem. That entry does not exist.");
        dashedLine();
    }

    /**
     * Prints the error message for when a user makes a command, but with incorrect formatting.
     * @param message a String that contains the details for why the command failed.
     */
    public void showInvalidInputError(String message) {
        dashedLine();
        System.out.println("Whoa. That command doesn't look right. Here's what seems to be wrong:\n" + message);
        dashedLine();
    }

    /**
     * Prints corresponding error message when there is a NumberFormatException.
     */
    public void showNumberFormatError() {
        //Todo:This can be removed and merged with showInvalidInputError in future iterations
        dashedLine();
        System.out.println("I only take integers for that command, kid.");
        dashedLine();
    }

    /**
     * Prints the success message when the given task in the list has been marked as completed.
     * @param task the task that has been marked as completed
     */
    public void showMarkSuccess(Task task) {
        dashedLine();
        System.out.println("Gotcha. Just marked this task as done:\n" + task);
        dashedLine();
    }

    /**
     * Prints the success message when the given task in the list has been marked as not completed.
     * @param task the task that has been marked as not completed
     */
    public void showUnmarkSuccess(Task task) {
        dashedLine();
        System.out.println("Gotcha. Just marked this task as not done:\n" + task);
        dashedLine();
    }

    /**
     * Prints success message when the given task has been added to the list.
     * @param task the task that was added to the list of tasks
     * @param list the list of tasks
     */
    public void showAddTaskSuccess(Task task, TaskList list) {
        dashedLine();
        System.out.println("Gotcha. Just added this task to the list:\n" + task + "\n");
        showListLength(list);
        dashedLine();
    }

    /**
     * Prints success message when the given task has been removed from the list.
     * @param task the task that was removed from the list of tasks
     * @param list the list of tasks
     */
    public void showDeleteSuccess(Task task, TaskList list) {
        dashedLine();
        System.out.println("Gotcha. Just marked this task as not done:\n" + task + "\n");
        showListLength(list);
        dashedLine();
    }

    /**
     * Prints all the elements in the provided list of tasks with numerical labels.
     * @param list the list of tasks to print
     */
    public void showListState(TaskList list) {
        dashedLine();
        for (int i = 1; i <= list.size(); i++){
            System.out.println(i + ". " + list.get(i-1));
        }
        dashedLine();
    }

    public void showListLength(TaskList list) {
        System.out.println("You have " + list.size() + " tasks left. Anything else?");
    }

}
