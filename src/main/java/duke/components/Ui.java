package duke.components;

import duke.commands.AddCommand;
import duke.commands.DeleteCommand;
import duke.commands.MarkCommand;
import duke.commands.UnmarkCommand;
import duke.exceptions.DukeException;
import duke.tasks.Task;

import java.util.Scanner;

public class Ui {
    Scanner inputScanner;
    public Ui() {
        this.inputScanner = new Scanner(System.in);
    }
    /**
     * Private method for printing list of tasks stored.
     */
    public void displayList(TaskList taskList) {
        System.out.println("Here are the tasks currently in your list: ");
        for (int i = 1; i <= taskList.size(); i++) {
            System.out.println(i + ". " + taskList.get(i));
        }
        System.out.println("End of task list. (currently contains "
                + taskList.size()
                + " tasks)");
    }


    public String readCommand() throws DukeException {

        String userIn = inputScanner.nextLine();
        if (userIn.isBlank()) {
            throw new DukeException("empty command received, type a command!");
        }
        return userIn;
    }

    public void showLine() {
        System.out.println("-------------------------------");
    }

    public void showError(String message) {
        System.out.println(message);
    }

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

    public void showAddCompletion(AddCommand addCommand, TaskList tasks) {
        System.out.println(
                "Got it. I've added this task:\n" +
                addCommand.getTaskToAdd()
        );
        showNumTasks(tasks);
    }

    public void showNumTasks(TaskList tasks) {
        System.out.println("\nNow you have "
                + tasks.size()
                + " tasks in the list.");
    }

    public void showMarkCompletion(MarkCommand markCommand, Task taskMarked) {
        System.out.println(
                "Nice! I've marked this task as done:\n" +
                taskMarked
        );
    }

    public void showExitMsg() {
        System.out.println(
                "Bye! See you soon!"
        );
    }

    public void showUnmarkCompletion(UnmarkCommand unmarkCommand, Task taskUnmarked) {
        System.out.println(
                "OK, I've marked this task as not done yet:\n" +
                taskUnmarked
        );
    }

    public void displayDeleteMsg(DeleteCommand deleteCommand, TaskList tasks) {
        System.out.println(
                "Noted. I've removed this task:\n" +
                deleteCommand.getTaskToDelete()
        );
        showNumTasks(tasks);
    }
}
