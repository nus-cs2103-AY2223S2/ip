package lele.ui;

import java.util.Scanner;

import lele.task.Task;
import lele.task.TaskList;


/**
 *  Handles what to show to the user in response
 *  to their commands.
 */
public class Ui {
    private final String separator;
    private final Scanner sc;
    private final StringBuilder sb;
    /**
     * Constructor to instantiate a String separator
     * and a Scanner instance to receive inputs.
     *
     */
    public Ui() {
        this.separator = "____________________________________________________________";
        this.sc = new Scanner(System.in);
        this.sb = new StringBuilder();
    }

    /**
     *  Displays a welcome message on
     *  startup of the program.
     */
    public void welcome() {
        System.out.println("Good ta see yer dawg, Lele's at yer service.\n");
    }

    /**
     * Ignores empty lines inputted by the user.
     *
     * @param inputLine Line that the user has inputted.
     * @return Returns a boolean indicating whether the line is empty.
     */
    private boolean shouldIgnore(String inputLine) {
        return inputLine.trim().isEmpty();
    }

    /**
     * Takes in the user's input (excluding empty lines).
     *
     * @return The string containing the user input.
     */
    public String readCommand() {
        String command = sc.nextLine();

        while (shouldIgnore(command)) {
            command = sc.nextLine();
        }

        return command;
    }

    /**
     * Helps to clear the strings contained in StringBuilder.
     * More efficient compared to creating a new instance of string builder
     * each time a function is called.
     */
    public void clearBuffer() {
        this.sb.setLength(0);
    }

    /**
     * Prints to the command line the separator line.
     */
    public void showLine() {
        System.out.println(this.separator);
    }

    /**
     * Displays a message when the user does not have an
     * existing data file.
     */
    public void showLoadingError() {
        showLine();
        System.out.println("No existing data, creating new files now");
        showLine();
    }

    /**
     * Prints to the command line the error message
     * received from the user's commands.
     *
     * @param message Receives the string from exception.
     */
    public String showError(String message) {
        return message;
    }

    /**
     * Prints to the command line a message when the user inputs
     * bye.
     */
    public String printBye() {
        return "See yer again RUFF!";
    }

    /**
     * Prints the list of tasks created by the user.
     *
     * @param taskList TaskList instance created by the user.
     * @return Output to user.
     */
    public String printTaskList(TaskList taskList) {
        clearBuffer();
        sb.append("Here are the tasks in your list!:\n");
        sb.append(taskList.printList());
        return sb.toString();
    }

    /**
     * Prints the message when a user marks a
     * task in the checklist.
     *
     * @param taskList TaskList created by the user.
     * @param index The index of the task marked by the user.
     * @return Output to user.
     */
    public String printMarkStatus(TaskList taskList, int index) {
        clearBuffer();
        sb.append("The task is marked, dawg\n");
        sb.append(taskList.getTask(index - 1));
        sb.append("\n");
        return sb.toString();
    }

    /**
     * Prints the message when a user unmarks a
     * task in the checklist.
     *
     * @param taskList TaskList created by the user.
     * @param index The index of the task unmarked by the user.
     * @return Output to user.
     */
    public String printUnMarkStatus(TaskList taskList, int index) {
        clearBuffer();
        sb.append("Gotcha dawg, unmarked\n");
        sb.append(taskList.getTask(index - 1));
        sb.append("\n");
        return sb.toString();
    }

    /**
     * Prints the message for when a user adds a task.
     *
     * @param taskList TaskList created by the user.
     * @param task The task to be added.
     * @return Output to user.
     */
    public String printAddTask(TaskList taskList, Task task) {
        clearBuffer();
        sb.append("Gotcha, I've added:\n");
        sb.append("  " + task + "\n");
        sb.append("Now you have " + taskList.listSize() + " in the list!\n");
        return sb.toString();
    }

    /**
     * Prints the message for when a user deletes a task.
     *
     * @param taskList TaskList created by the user.
     * @param task The task to be deleted.
     * @return Output to user.
     */
    public String printDelete(TaskList taskList, Task task) {
        clearBuffer();
        sb.append("Removing your task? It's gone now RUFF:\n");
        sb.append("  " + task + "\n");
        sb.append("Now you have " + taskList.listSize() + " in the list!\n");
        return sb.toString();
    }

    /**
     * Prints the response to the command line when user inputs
     * a find command, with the tasks matching the regex.
     *
     * @param taskList Current task list created by the user.
     * @param regex Regex queried by the user.
     */
    public String printFind(TaskList taskList, String regex) {
        clearBuffer();
        sb.append("Here are the matching tasks in your list:\n");
        sb.append(taskList.findTask(regex));
        return sb.toString();
    }

    /**
     * Prints the response to an Undo command.
     *
     * @param index Versions to be undone by.
     * @return String representation of response.
     */
    public String printUndo(int index) {
        if (index <= 0) {
            return "Please give a number more than 0!";
        } else if (index == 1) {
            return "Your list has been undone by 1 version :D";
        }
        return "Your list has been undone by " + index + " versions :D";
    }
}
