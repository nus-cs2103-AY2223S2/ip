package lele.ui;

import lele.task.Task;
import lele.task.TaskList;
import java.util.Scanner;

/**
 *  Handles what to show to the user in response
 *  to their commands.
 */
public class Ui {
    private final String separator;
    private final Scanner sc;

    /**
     * Constructor to instantiate a String separator
     * and a Scanner instance to receive inputs.
     *
     */
    public Ui() {
        this.separator = "____________________________________________________________";
        this.sc = new Scanner(System.in);
    }

    /**
     *  Displays a welcome message on
     *  startup of the program.
     */
    public void welcome() {
        String dog = "⠀⠀⠀⠀⠀⠀⠀⢀⣀⣀⠀⣀⣀⣀⣀⢀⣀⣀⣀⣀⣀⣀⣀⣀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⣀⣰⣿⣿⡻⠟⠋⠉⠉⣻⠟⠉⠉⠉⠛⢯⡛⢿⣿⣷⣤⣀⠀⠀⠀⠀⠀\n" +
                "⠀⣠⣴⠾⠛⢋⣿⠟⠋⠀⠀⠀⠀⢀⡟⠀⠀⠀⠀⠀⠀⠈⠂⣹⣿⡈⠙⠻⢶⣄⡀⠀\n" +
                "⣸⠏⠀⠀⠀⣾⣋⣀⣀⡀⠀⠀⠀⢸⠁⠀⠀⢀⣀⣀⣀⡀⠀⠈⠻⣧⠀⠀⠀⠉⠻⣦\n" +
                "⢿⡀⠀⣿⣿⠟⣫⣽⣿⣿⣿⣿⣶⣶⣶⡶⠛⣻⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⢀⣿\n" +
                "⠸⣧⠀⠈⣿⢸⣿⣿⣿⣿⣿⣿⣿⠁⢹⡇⣼⣿⣿⣿⣿⣿⣿⣿⠁⣼⡇⠀⠀⠀⣼⠇\n" +
                "⠀⠹⣷⡀⢹⣿⣿⣿⣿⣿⣿⣿⠏⠀⠀⢻⣿⣿⣿⣿⣿⣿⣿⠏⠀⣿⡇⠀⣠⡾⠋⠀\n" +
                "⠀⠀⠈⢿⣿⢿⡿⠿⠿⣿⠟⠉⠀⠀⠀⠀⠙⠛⢿⡿⠿⠛⠉⠀⠀⡿⣷⣾⠏⠀⠀⠀\n" +
                "⠀⠀⠀⠈⠋⠘⣷⠀⢀⡿⢰⣾⣟⣛⣿⣿⣷⡄⠀⢻⣆⠀⠀⠀⢰⡇⠘⠋⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠹⣧⣼⠃⠈⣧⣼⣿⣇⣈⣿⠃⠀⠀⣿⣀⣀⣴⠟⠁⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠙⢿⣆⠀⠈⠙⢿⡛⠉⠁⠀⠀⣠⡿⣿⣿⠁⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠈⣿⡿⢶⣶⣾⣿⣶⣤⣤⣶⢿⣼⣿⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⢹⣧⡼⠁⠉⠏⠁⠈⢹⣠⣾⣿⠏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢿⣧⠀⠀⠀⠀⠀⣸⣿⣿⠏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣷⣤⣤⣤⡾⣿⡿⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⠿⢶⣾⣶⠾⠛⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀";
        System.out.println("Good ta see yer dawg, Lele's at yer service.\n" + dog);
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
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Prints to the command line a message when the user inputs
     * bye.
     */
    public void printBye() {
        System.out.println("See yer again RUFF!");
    }

    /**
     * Prints the list of tasks created by the user.
     *
     * @param taskList TaskList instance created by the user.
     */
    public void printTaskList(TaskList taskList) {
        System.out.println("Here are the tasks in your list dawg:");
        taskList.printList();
    }

    /**
     * Prints the message when a user marks a
     * task in the checklist.
     *
     * @param taskList TaskList created by the user.
     * @param index The index of the task marked by the user.
     */
    public void printMarkStatus(TaskList taskList, int index) {
        taskList.markStatus(index);
        System.out.println("The task is marked, dawg");
        System.out.println(taskList.getTask(index - 1));
    }

    /**
     * Prints the message when a user unmarks a
     * task in the checklist.
     *
     * @param taskList TaskList created by the user.
     * @param index The index of the task unmarked by the user.
     */
    public void printUnMarkStatus(TaskList taskList, int index) {
        taskList.unMarkStatus(index);
        System.out.println("Gotcha dawg, unmarked");
        System.out.println(taskList.getTask(index - 1));
    }

    /**
     * Prints the message for when a user adds a task.
     *
     * @param taskList TaskList created by the user.
     * @param task The task to be added
     */
    public void printAddTask(TaskList taskList, Task task) {
        System.out.println("Gotcha, I've added:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskList.listSize() + " in the list!");
    }

    /**
     * Prints the message for when a user deletes a task.
     *
     * @param taskList TaskList created by the user.
     * @param task The task to be deleted.
     */
    public void printDelete(TaskList taskList, Task task) {
        System.out.println("Removing your task? It's gone now RUFF:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskList.listSize() + " in the list!");
    }

    /**
     * Prints the response to the command line when user inputs
     * a find command, with the tasks matching the regex.
     *
     * @param taskList Current task list created by the user.
     * @param regex Regex queried by the user.
     */
    public void printFind(TaskList taskList, String regex) {
        System.out.println("Here are the matching tasks in your list:");
        taskList.findTasks(regex);
    }
}
