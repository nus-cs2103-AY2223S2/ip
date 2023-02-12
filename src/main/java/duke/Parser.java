package duke;

import java.time.format.DateTimeParseException;

import duke.task.Task;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;
import javafx.application.Platform;

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
    public String readInput(String[] input, TaskList taskList) throws DukeException {
        switch (input[0]) {
        case "list":
            return taskList.list();
        case "mark":
            return mark(input, taskList);
        case "unmark":
            return unmark(input, taskList);
        case "todo":
            return todo(input, taskList);
        case "deadline":
            return deadline(input, taskList);
        case "event":
            return event(input, taskList);
        case "delete":
            return delete(input, taskList);
        case "find":
            return find(input, taskList);
        case "bye":
            Platform.exit();
            return "Goodbye!";
        default:
            throw new DukeException("Sorry I do not understand the command");
        }
    }

    /**
     * Marks a specific task as done and returns a String to notify user.
     * @param input User's input
     * @param taskList User's TaskList
     * @return String Message to update user upon success
     * @throws DukeException If user enters invalid number or task that has not been created.
     */
    public String mark(String[] input, TaskList taskList) throws DukeException {
        if (input.length == 1) {
            throw new DukeException("Mark needs a number.");
        }
        int index;
        try {
            index = Integer.parseInt(input[1]);
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid number.");
        }
        boolean isNotWithinTaskListSize = index > taskList.size();
        boolean isSmallerThan1 = index <= 0;
        if (isNotWithinTaskListSize || isSmallerThan1) {
            throw new DukeException("Invalid task.");
        }
        assert index > 0 : "Number less than equal 0.";
        return taskList.markTask(index - 1);
    }

    /**
     * Marks a specific task as not done and returns a String to notify user.
     * @param input User's input
     * @param taskList User's TaskList
     * @return String Message to update user upon success
     * @throws DukeException If user enters invalid number or task that has not been created.
     */
    public String unmark(String[] input, TaskList taskList) throws DukeException {
        if (input.length == 1) {
            throw new DukeException("Unmark needs a number.");
        }
        int index;
        try {
            index = Integer.parseInt(input[1]);
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid number.");
        }
        boolean isNotWithinTaskListSize = index > taskList.size();
        boolean isSmallerThan1 = index <= 0;
        if (isNotWithinTaskListSize || isSmallerThan1) {
            throw new DukeException("Invalid task.");
        }
        assert index > 0 : "Number less than equal 0.";
        return taskList.unmarkTask(index - 1);
    }

    /**
     * Adds a Todo task into the users TaskList.
     * @param input User's input
     * @param taskList User's TaskList
     * @return String Message to update user upon success
     * @throws DukeException If user did not provide name of Todo.
     */
    public String todo(String[] input, TaskList taskList) throws DukeException {
        if (input.length == 1) {
            throw new DukeException("todo needs a description");
        }
        Task t = new Todo(input[1].strip());
        taskList.addTask(t);
        return "Added new todo:\n  " + t + "\nNumber of tasks: " + taskList.size();
    }

    /**
     * Adds a Deadline task into the users TaskList.
     * @param input User's input
     * @param taskList User's TaskList
     * @return String Message to update user upon success
     * @throws DukeException If user did not enter /by date or invalid date format.
     */
    public String deadline(String[] input, TaskList taskList) throws DukeException {
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
            return "Added new deadline:\n  " + t + "\nNumber of tasks: " + taskList.size();
        } catch (DateTimeParseException e) {
            throw new DukeException("Date after /by needs to be in format yyyy-mm-dd.");
        }
    }

    /**
     * Adds an Event task into the users TaskList.
     * @param input User's input
     * @param taskList User's TaskList
     * @return String Message to update user upon success
     * @throws DukeException If user did not enter /from or /to or invalid date format.
     */
    public String event(String[] input, TaskList taskList) throws DukeException {
        boolean isWithoutInput = input.length == 1;
        boolean isWithoutFrom = !input[1].contains("/from");
        boolean isWithoutTo = !input[1].contains("/to");
        if (isWithoutInput || isWithoutFrom || isWithoutTo ) {
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
            return "Added new event:\n  " + t + "\nNumber of tasks: " + taskList.size();
        } catch (DateTimeParseException e) {
            throw new DukeException("Date after /from and /to needs to be in format yyyy-mm-dd.");
        }
    }

    /**
     * Deletes a task from the user's TaskList.
     * @param input User's input
     * @param taskList User's TaskList
     * @return String Message to update user upon success
     * @throws DukeException If user enters invalid number.
     */
    public String delete(String[] input, TaskList taskList) throws DukeException {
        if (input.length == 1) {
            throw new DukeException("Delete needs a number.");
        }
        int index;
        try {
            index = Integer.parseInt(input[1]);
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid number.");
        }
        boolean isNotWithinTaskListSize = index > taskList.size();
        boolean isSmallerThan1 = index <= 0;
        if (isNotWithinTaskListSize || isSmallerThan1) {
            throw new DukeException("Invalid task.");
        }
        assert index > 0 : "Number less than equal 0.";
        return taskList.deleteTask(index - 1);
    }

    /**
     * Returns a String containing the user's task that matches the given keyword.
     * @param input User's input
     * @param taskList User's TaskList
     * @return String User's task that match given keyword
     * @throws DukeException If user did not enter a keyword.
     */
    public String find(String[] input, TaskList taskList) throws DukeException {
        if (input.length == 1) {
            throw new DukeException("Find needs a keyword.");
        }
        return "Here are the matching tasks in your list:\n" + taskList.findTask(input[1].strip());
    }
}
