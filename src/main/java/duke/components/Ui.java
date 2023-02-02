package duke.components;

import duke.commands.AddCommand;
import duke.commands.DeleteCommand;
import duke.commands.MarkCommand;
import duke.commands.UnmarkCommand;
import duke.exceptions.DukeException;
import duke.tasks.Task;

import java.util.Scanner;

/***
 * This is the UI class for Duke, the CLI task manager.
 * This class is used to handle all outputs printed to
 * the user through the terminal, as well as all inputs
 * to the Parser class.
 */

public class Ui {
    Scanner inputScanner;

    /**
     * Returns a new UI object. Initializes scanner for inputs.
     */
    public Ui() {
        this.inputScanner = new Scanner(System.in);
    }

    /**
     * Prints all the Tasks in the TaskList provided.
     * @param taskList TaskList to be printed.
     */
    public void displayList(TaskList taskList) {
        System.out.println("Here are the tasks currently in your list: ");
        for (int i = 1; i <= taskList.size(); i++) {
            System.out.println(i + ". " + taskList.getTask(i));
        }
        System.out.println("End of task list. (currently contains " + taskList.size() + " tasks)");
    }

    /**
     * Returns user input through the CLI as a String.
     *
     * @return String of user input.
     * @throws DukeException if empty command is passed.
     */
    public String readCommand() throws DukeException {

        String userIn = inputScanner.nextLine();
        if (userIn.isBlank()) {
            throw new DukeException("empty command received, type a command!");
        }
        return userIn;
    }

    /**
     * Prints a dividing line.
     */
    public void showLine() {
        System.out.println("-------------------------------");
    }

    /**
     * Prints the error message passed to it.
     * @param message String of error message.
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Prints the welcome message for the user.
     */
    public void showWelcome() {
        System.out.println(
                "Hello! I'm Duke!\n" +
                "currently my functionality includes:\n" +
                "bye\n" +
                "todo\n" +
                "deadline\n" +
                "event\n" +
                "list\n" +
                "mark\n" +
                "unmark\n" +
                "delete\n" +
                "try typing a command! :D");
    }

    /**
     * Prints the completion message after a Task is successfully added.
     * @param addCommand AddCommand responsible for the added Task.
     * @param tasks TaskList that was added to.
     */
    public void showAddCompletion(AddCommand addCommand, TaskList tasks) {
        System.out.println(
                "Got it. I've added this task:\n" +
                addCommand.getTaskToAdd()
        );
        showNumTasks(tasks);
    }

    /**
     * Prints the message describing the number of tasks in the TaskList provided.
     * @param tasks TaskList to be described.
     */
    public void showNumTasks(TaskList tasks) {
        System.out.println("\nNow you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Prints the completion message after a Task is successfully marked.
     * @param markCommand MarkCommand responsible for the added Task.
     * @param taskMarked Task that was marked.
     */
    public void showMarkCompletion(MarkCommand markCommand, Task taskMarked) {
        System.out.println(
                "Nice! I've marked this task as done:\n" +
                taskMarked
        );
    }

    /**
     * Prints the exit message when exiting Duke.
     */
    public void showExitMsg() {
        System.out.println(
                "Bye! See you soon!"
        );
    }
    /**
     * Prints the completion message after a Task is successfully unmarked.
     * @param unmarkCommand UnmarkCommand responsible for the added Task.
     * @param taskUnmarked Task that was unmarked.
     */
    public void showUnmarkCompletion(UnmarkCommand unmarkCommand, Task taskUnmarked) {
        System.out.println(
                "OK, I've marked this task as not done yet:\n" +
                taskUnmarked
        );
    }
    /**
     * Prints the completion message after a Task is successfully deleted.
     * @param deleteCommand DeleteCommand responsible for the deleted Task.
     * @param tasks TaskList that was deleted from.
     */
    public void displayDeleteMsg(DeleteCommand deleteCommand, TaskList tasks) {
        System.out.println(
                "Noted. I've removed this task:\n" +
                deleteCommand.getTaskToDelete()
        );
        showNumTasks(tasks);
    }
}
