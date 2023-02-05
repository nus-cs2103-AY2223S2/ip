package duke;

import java.time.format.DateTimeParseException;

import duke.task.Task;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;

/**
 * Represents a class to decipher user's commands.
 */
public class Parser {
    public Parser() {};

    /**
     * Reads the command that the user input, calls the respective functions.
     * @param input User's input command.
     * @param taskList User's TaskList.
     * @throws DukeException If user enters invalid input.
     */
    public void readInput(String[] input, TaskList taskList) throws DukeException {
        switch (input[0]) {
        case "list":
            taskList.list();
            break;
        case "mark":
            mark(input, taskList);
            break;
        case "unmark":
            unmark(input, taskList);
            break;
        case "todo":
            todo(input, taskList);
            break;
        case "deadline":
            deadline(input, taskList);
            break;
        case "event":
            event(input, taskList);
            break;
        case "delete":
            delete(input, taskList);
            break;
        default:
            throw new DukeException("Sorry I do not understand the command");
        }
    }

    /**
     * Method to handle the mark command.
     * @param input User's input
     * @param taskList User's TaskList
     * @throws DukeException If user enters invalid number or task that has not been created.
     */
    public void mark(String[] input, TaskList taskList) throws DukeException {
        if (input.length == 1) {
            throw new DukeException("Mark needs a number.");
        }
        int index;
        try {
            index = Integer.parseInt(input[1]);
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid number.");
        }
        if (index > taskList.size()) {
            throw new DukeException("Invalid task.");
        }
        taskList.markTask(Integer.parseInt(input[1]) - 1);
    }

    /**
     * Method to handle the unmark command.
     * @param input User's input
     * @param taskList User's TaskList
     * @throws DukeException If user enters invalid number or task that has not been created.
     */
    public void unmark(String[] input, TaskList taskList) throws DukeException {
        if (input.length == 1) {
            throw new DukeException("Unmark needs a number.");
        }
        int index;
        try {
            index = Integer.parseInt(input[1]);
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid number.");
        }
        if (index > taskList.size()) {
            throw new DukeException("Invalid task.");
        }
        taskList.unmarkTask(Integer.parseInt(input[1]) - 1);
    }

    /**
     * Method to handle the todo command.
     * @param input User's input
     * @param taskList User's TaskList
     * @throws DukeException If user did not provide name of Todo.
     */
    public void todo(String[] input, TaskList taskList) throws DukeException {
        if (input.length == 1) {
            throw new DukeException("todo needs a description");
        }
        Task t = new Todo(input[1].strip());
        taskList.addTask(t);
        System.out.println("Added new todo:\n  " + t + "\nNumber of tasks: " + taskList.size());
    }

    /**
     * Method to handle the deadline command.
     * @param input User's input
     * @param taskList User's TaskList
     * @throws DukeException If user did not enter /by date or invalid date format.
     */
    public void deadline(String[] input, TaskList taskList) throws DukeException {
        if (input.length == 1 || !input[1].contains("/by")) {
            throw new DukeException("Deadline needs a /by.");
        }
        String[] tempInput = input[1].strip().split("/by ");
        if (tempInput.length == 1) {
            throw new DukeException("/by needs a date/time.");
        }
        try {
            Task t = new Deadline(tempInput[0].strip(), tempInput[1].strip());
            taskList.addTask(t);
            System.out.println("Added new deadline:\n  " + t + "\nNumber of tasks: " + taskList.size());
        } catch (DateTimeParseException e) {
            throw new DukeException("Date after /by needs to be in format yyyy-mm-dd.");
        }
    }

    /**
     * Method to handle the event command.
     * @param input User's input
     * @param taskList User's TaskList
     * @throws DukeException If user did not enter /from or /to or invalid date format.
     */
    public void event(String[] input, TaskList taskList) throws DukeException {
        if (input.length == 1 || !input[1].contains("/from") || !input[1].contains("/to") ) {
            throw new DukeException("Event needs a /from and /to.");
        }
        String[] tempInput = input[1].split("/");
        String[] from = tempInput[1].split(" ",2);
        String[] to = tempInput[2].split(" ",2);
        if (from.length == 1 || to.length == 1) {
            throw new DukeException("/from and /to needs a date/time.");
        }
        try {
            Task t = new Event(tempInput[0].strip(), from[1].strip(), to[1].strip());
            taskList.addTask(t);
            System.out.println("Added new event:\n  " + t + "\nNumber of tasks: " + taskList.size());
        } catch (DateTimeParseException e) {
            throw new DukeException("Date after /from and /to needs to be in format yyyy-mm-dd.");
        }
    }

    /**
     * Method to handle the delete command.
     * @param input User's input
     * @param taskList User's TaskList
     * @throws DukeException If user enters invalid number.
     */
    public void delete(String[] input, TaskList taskList) throws DukeException {
        if (input.length == 1) {
            throw new DukeException("Delete needs a number.");
        }
        int index;
        try {
            index = Integer.parseInt(input[1]);
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid number.");
        }
        if (index > taskList.size()) {
            throw new DukeException("Invalid task.");
        }
        taskList.deleteTask(index - 1);
    }
}
