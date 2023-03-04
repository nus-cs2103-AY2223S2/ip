package duke.ui;
import java.util.Scanner;

import duke.action.Task;
import duke.data.TaskList;
import duke.data.TypeOfTask;

/**
 * User Interface Class for handling user's interactions
 *
 * @author Haiqel Bin Hanaffi (Acerizm)
 */
public class Ui {

    private Scanner scanner;

    /**
     * Default constructor
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome message with customized logo
     */
    public void displayWelcome() {
        String personalLogo = "                      - \n"
                             + "    /                (_) \n"
                             + "   /  \\   ___ ___ _ __ _ _____ __ ___ \n"
                             + "  / /  \\ / __/ _ \\ '__| |_  / '_ ` _ \\ \n"
                             + " / ____ \\ (_|  __/ |  | |/ /| | | | | | \n"
                             + "/_/    \\_\\___\\___|_|  |_/___|_| |_| |_| \n";

        System.out.println("Hi there! I am \n" + personalLogo);
        this.showLine();
        System.out.println("What can I do for you?");
        this.showLine();
    }

    /**
     * Closes the scanner instance
     */
    public void close() {
        this.scanner.close();
    }

    /**
     * Shows the long repeated lines for UI
     */
    public void showLine() {
        System.out.println("*-".repeat(100));
    }

    /**
     * Gets the result of the commands given by the user
     * @param type Type of task
     * @param task Task instance
     * @param taskList List of tasks
     */
    public String getResult(TypeOfTask type, Task task, TaskList taskList) {
        String result = "";
        if (type == TypeOfTask.bye) {
            result += "Bye! I'm always listening and watching you";
        } else if (type == TypeOfTask.list) {
            result += "Be a good boy and see your current tasks now!";
        } else if (type == TypeOfTask.mark) {
            // for marking tasks
            result += "Well done! Task is marked and you'll receive pets ;) \n" + task.toString();
        } else if (type == TypeOfTask.unmark) {
            // for unmarking tasks
            result += "Bad boy! Task is unmarked and no food for you \n" + task.toString();
        } else if (type == TypeOfTask.todo) {
            // for todo tasks
            result += "Didn't know you have so much on your hands!"
                    + " I'll make sure I remember these tasks: \n" + task.toString()
                    + "\n"
                    + String.format("Now you have %d tasks in the list", taskList.getSize());
        } else if (type == TypeOfTask.deadline) {
            result += "Don't forget about these deadlines or no food for you. \n"
                    + "I've added this task: \n" + task.toString()
                    + "\n" + String.format("Now you have %d tasks in the list", taskList.getSize());
        } else if (type == TypeOfTask.event) {
            // for event
            result += "Busy with other events? How silly. \nI've added this task:" + task.toString()
                    + "\n"
                    + String.format("Now you have %d tasks in the list", taskList.getSize());
        } else if (type == TypeOfTask.delete) {
            result += String.format("Now you have %d tasks in the list", taskList.getSize());
        } else if (type == TypeOfTask.find) {
            result += "Here are the matching tasks in your list";
        } else {
            result += "Don't be a bad boy and type in something useful"
                    + "\nWhat can I do for you?";
        }
        return result;
    }

    /**
     * Reads the command from the user's inputs
     * @return the user's inputs
     * @throws Exception when user does not type anything and immediately enters an empty response or command.
     */
    public String readCommand() throws Exception {
        // this is what I want
        return this.scanner.nextLine();
    }
}
