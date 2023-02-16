package duke.helper;

import java.util.ArrayList;
import java.util.Scanner;

import duke.exception.InvalidCommandException;
import duke.task.Task;

/**
 * Ui class that handles all the interactions with the users
 */
public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints the delete message when a task is removed
     *
     * @param task task to be removed
     * @param size number of remaining tasks
     */
    public String outputDeleteMsg(Task task, int size) {
        assert size >= 0 : "Size of TaskList should be zero or more";
        StringBuilder output = new StringBuilder("Noted. I've removed this task:\n");
        output.append(task).append(String.format("\nNow you have %d tasks in the list.", size));
        return output.toString();
    }

    /**
     * Prints the message for mark and unmark commands
     *
     * @param isDone whether a task will be marked or unmarked
     * @param taskToMark task to be marked or unmarked
     */
    public String outputMarkMsg(boolean isDone, Task taskToMark) {
        StringBuilder output = new StringBuilder();
        if (isDone) {
            output.append("Nice! I've marked this task as done:\n");
        } else {
            output.append("OK, I've marked this task as not done yet:\n");
        }
        return output.append(taskToMark).toString();
    }

    /**
     * Prints the message when a task is added
     *
     * @param task task to be added
     * @param size the number of tasks after the task is added
     */
    public String outputAddTaskMsg(Task task, int size) {
        assert size >= 0 : "Size of TaskList should be zero or more";
        StringBuilder output = new StringBuilder("Got it. I've added this task:\n");
        output.append(task).append("\nNow you have " + size + " tasks in the list.");
        return output.toString();
    }

    /**
     * prints the message for searching a file
     *
     * @param taskList List of all the tasks
     */
    public String outputFilterMsg(ArrayList<Task> taskList) {
        StringBuilder output = new StringBuilder("Here are the matching tasks in your list:\n");
        for (Task task : taskList) {
            output.append(task).append("\n");
        }
        return output.toString();
    }

    /**
     * Returns the help message
     *
     * @param command the command the user needs help with
     * @return help message
     * @throws InvalidCommandException Throws when help command is followed by something that is not a command
     */
    public String outputHelpMsg(String command) throws InvalidCommandException {
        switch (command) {
        case "list":
            return "Enter: list";
        case "mark":
            return "Enter: mark <task number>";
        case "unmark":
            return "Enter: unmark <task number>";
        case "todo":
            return "Enter: todo <task>";
        case "deadline":
            return "Enter: deadline <task> /by <yyyy-MM-dd>T<HH:MM>";
        case "event":
            return "Enter: event <task> /from <yyyy-MM-dd>T<HH:MM> /to <yyyy-MM-dd>T<HH:MM>";
        case "delete":
            return "Enter: delete <task number>";
        case "find":
            return "Enter: find <keyword>";
        case "bye":
            return "Enter: bye";
        default:
            throw new InvalidCommandException();
        }
    }
}
