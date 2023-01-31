package duke.ui;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.task.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles interactions with the user.
 */
public class Ui {
    private final Scanner SCANNER = new Scanner(System.in);

    public String showLine() {
        return "________________________________________________________________\n";
    }

    public String readCommand() throws DukeException {
        String command = "";
        if (SCANNER.hasNextLine()) {
            command = SCANNER.nextLine();
            if (command.contains("~")) {
                throw new DukeException("Fake Duke doesn't like it when the input has the symbol ~.");
            }
        }
        return command;
    }

    /**
     * Prints out the greeting for Fake Duke.
     */
    public void showWelcome() {
       System.out.println(this.showLine()
                + "Hello!~ I'm the one and only ✨ FAKE DUKE ✨\nWhat can I do for you?\n"
                + this.showLine());
    }

    /**
     * Prints message when task is added into task list.
     *
     * @param task Task that is added into the task list.
     * @param taskList List of tasks.
     */
    public void showAdd(Task task, TaskList taskList) {
        System.out.println(this.showLine()
                + "Got it. I've added this task:\n"
                + task.toString()
                + "\nNow you have "
                + taskList.getSize()
                + " tasks in the list.\n"
                + this.showLine());
    }

    /**
     * Prints message when task is deleted from the list of tasks.
     *
     * @param taskString Description of task that has been deleted from the list of tasks.
     * @param taskList List of tasks.
     */
    public void showDelete(String taskString, TaskList taskList) {
        System.out.println(this.showLine()
                + "Noted. I've removed this task:\n"
                + taskString
                + "\nNow you have "
                + taskList.getSize()
                + " tasks in the list.\n"
                + this.showLine());
    }

    /**
     * Prints message when task is marked as done.
     *
     * @param taskString Task in String that has been marked as done.
     */
    public void showMark(String taskString) {
        System.out.println(this.showLine()
                + "Nice! I've marked this task as done:\n"
                + taskString
                + "\n"
                + this.showLine());
    }

    /**
     * Prints message when task is marked as done.
     *
     * @param taskString Task in String that has been unmarked as undone.
     */
    public void showUnmark(String taskString) {
        System.out.println(this.showLine()
                + "OK, I've marked this task as not done yet:\n"
                + taskString
                + "\n"
                + this.showLine());
    }

    /**
     * Prints the list of tasks.
     *
     * @param taskList List of tasks.
     * @throws DukeException Throws exception if unable to get task.
     */
    public void showList(TaskList taskList) throws DukeException {
        System.out.println(this.showLine()
                + "Here are the tasks in your list:");
        for (int i = 1; i <= taskList.getSize(); i++) {
            System.out.println(i + "." + taskList.getTask(i - 1).toString());
        }
        System.out.println(this.showLine());
    }


    /**
     * Prints an exit message.
     */
    public void showExit() {
        System.out.println(this.showLine()
                + "Hope I have been useful to you.\n"
                + "See you again soon. Bye!~\n"
                + this.showLine());
    }

    /**
     * Prints error message.
     *
     * @param error Error message.
     */
    public void showError(String error) {
        System.out.println(this.showLine() + error + this.showLine());
    }

    /**
     * Prints the subset list of tasks that matches the keyword provided by the user.
     *
     * @param foundTasks Subset list of tasks.
     */
    public void showFind(ArrayList<Task> foundTasks) {
        System.out.println(this.showLine() + "Here are the matching tasks in your list:");
        for (int i = 1; i <= foundTasks.size(); i++) {
            System.out.println(i + "." + foundTasks.get(i - 1));
        }
        System.out.println(this.showLine());
    }

    /**
     * Returns datetime in String for printing.
     *
     * @param dateTime Datetime of Task.
     * @return String representation of datetime.
     */
    public String getStringDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E dd-MM-yyyy HH:mma");
        return dateTime.format(formatter);
    }
}
