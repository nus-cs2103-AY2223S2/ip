package duke;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.Scanner;

/**
 * Parses all user input into commands such as bye, list, mark, unmark, and the 3 tasks ToDo, Deadline and Event.
 * Executes the commands.
 */
public class Parser {
    private Scanner sc;
    private boolean parserIsDone;

    /**
     * Constructor to create a Parser object.
     *
     * @param sc The Scanner that takes input from the user.
     */
    public Parser(Scanner sc) {
        this.sc = sc;
        this.parserIsDone = false;
    }

    /**
     * Parses the user input into commands for Duke.
     *
     * @param tasks The task list to be modified.
     * @return The task list after parsing through user input from the scanner.
     */
    public TaskList parse(TaskList tasks) throws DukeException {
        String input = sc.nextLine();
        if (isBye(input)) {
            return bye(tasks);
        } else if (isList(input)) {
            Ui.listMessage(tasks);
        } else if (isMark(input, tasks.size())) {
            return mark(input, tasks);
        } else if (isUnMark(input, tasks.size())) {
            return unMark(input, tasks);
        } else if (isDelete(input, tasks.size())) {
            return delete(input, tasks);
        } else {
            if (isToDo(input)) {
                tasks.add(newToDo(input, tasks.size()));
            } else if (isDeadline(input)) {
                tasks.add(newDeadline(input, tasks.size()));
            } else if (isEvent(input)) {
                tasks.add(newEvent(input, tasks.size()));
            } else {
                throw new DukeException("Please input a task with either todo, deadline or event prefixed!");
            }
        }
        return tasks;
    }

    /**
     * Checks if the parser is done parsing.
     *
     * @return The boolean representing whether the parser is done parsing.
     */
    public boolean notDone() {
        return !parserIsDone;
    }

    /**
     * Checks if the user input is a bye command.
     *
     * @param input The input String.
     * @return The boolean representing whether the user input is a bye command.
     */
    public boolean isBye(String input) {
        return "bye".equals(input);
    }

    /**
     * Checks if the user input is a list command.
     *
     * @param input The input String.
     * @return The boolean representing whether the user input is a list command.
     */
    public boolean isList(String input) {
        return "list".equals(input);
    }

    /**
     * Checks if the user input is a mark command.
     *
     * @param input The input String.
     * @return The boolean representing whether the user input is a mark command.
     */
    public boolean isMark(String input, int listSize) {
        if (input.length() >=  6 && input.startsWith("mark ") && isNumeric(input.substring(5))) {
            int taskindex = Integer.parseInt(input.substring(5)) - 1;
            return !(taskindex < 0 || taskindex > listSize - 1);
        }
        return false;
    }

    /**
     * Checks if the user input is an unmark command.
     *
     * @param input The input String.
     * @return The boolean representing whether the user input is a unmark command.
     */
    public boolean isUnMark(String input, int listSize) {
        if (input.length() >=  8 && input.startsWith("unmark ") && isNumeric(input.substring(7))) {
            int taskindex = Integer.parseInt(input.substring(7)) - 1;
            return !(taskindex < 0 || taskindex > listSize - 1);
        }
        return false;
    }

    /**
     * Checks if the user input is a delete command.
     *
     * @param input The input String.
     * @return The boolean representing whether the user input is a delete command.
     */
    public boolean isDelete(String input, int listSize) {
        if (input.length() >=  8 && input.startsWith("delete ") && isNumeric(input.substring(7))) {
            int taskindex = Integer.parseInt(input.substring(7)) - 1;
            return !(taskindex < 0 || taskindex > listSize - 1);
        }
        return false;
    }

    /**
     * Checks if the user input is a ToDo task.
     *
     * @param input The input String.
     * @return The boolean representing whether the user input is a ToDo task.
     */
    public boolean isToDo(String input) throws DukeException {
        if (input.length() >= 4 && input.startsWith("todo")) {
            if (input.equals("todo") || input.substring(4).isBlank()) {
                throw new DukeException("TODO needs a description!");
            } else return input.startsWith("todo ");
        }
        return false;
    }

    /**
     * Checks if the user input is a Deadline task.
     *
     * @param input The input String.
     * @return The boolean representing whether the user input is a Deadline task.
     */
    public boolean isDeadline(String input) throws DukeException {
        if (input.length() >= 8 && input.startsWith("deadline")) {
            if (input.equals("deadline") || input.substring(8).isBlank() || input.equals("deadline /by") || input.equals("deadline /by ")) {
                throw new DukeException("DEADLINE needs a description!");
            } else return input.contains(" /by ");
        }
        return false;
    }

    /**
     * Checks if the user input is an Event task.
     *
     * @param input The input String.
     * @return The boolean representing whether the user input is an Event task.
     */
    public boolean isEvent(String input) throws DukeException {
        if (input.length() >= 5 && input.startsWith("event")) {
            if (input.equals("event") || input.substring(5).isBlank()) {
                throw new DukeException("EVENT needs a description!");
            }
            int fromdex = input.indexOf(" /from ");
            int todex = input.indexOf(" /to ");
            if (fromdex + 7 > todex) {
                throw new DukeException("What are you saying?");
            }
            return true;
        }
        return false;
    }

    /**
     * Ends the parser.
     *
     * @param tasks The current Task List.
     * @return The current Task List.
     */
    public TaskList bye(TaskList tasks) {
        parserIsDone = true;
        return tasks;
    }

    /**
     * Marks the user-specified item in the Task List.
     *
     * @param input The user input.
     * @param tasks The current Task List.
     * @return The Task List with the specified item marked.
     */
    public TaskList mark(String input, TaskList tasks) {
        int taskindex = Integer.parseInt(input.substring(5)) - 1;
        tasks.get(taskindex).markAsDone();
        Ui.markMessage(tasks.get(taskindex));
        return tasks;
    }

    /**
     * Unmarks the user-specified item in the Task List.
     *
     * @param input The user input.
     * @param tasks The current Task List.
     * @return The Task List with the specified item unmarked.
     */
    public TaskList unMark(String input, TaskList tasks) {
        int taskindex = Integer.parseInt(input.substring(7)) - 1;
        tasks.get(taskindex).markAsNotDone();
        Ui.unmarkMessage(tasks.get(taskindex));
        return tasks;
    }

    /**
     * Deletes the user-specified item in the Task List.
     *
     * @param input The user input.
     * @param tasks The current Task List.
     * @return The Task List with the specified item deleted.
     */
    public TaskList delete(String input, TaskList tasks) {
        int taskindex = Integer.parseInt(input.substring(7)) - 1;
        tasks.remove(taskindex);
        Ui.deleteMessage(tasks.get(taskindex));
        return tasks;
    }

    /**
     * Creates a new ToDo task and prints a message for the user
     * confirming the addition of the task to the Task List.
     *
     * @param input The user input.
     * @param taskSize The current Task List size.
     * @return The created task.
     */
    public Task newToDo(String input, int taskSize) {
        Task task = new ToDo(input);
        Ui.addMessage(task, taskSize);
        return task;
    }

    /**
     * Creates a new Deadline task and prints a message for the user
     * confirming the addition of the task to the Task List.
     *
     * @param input The user input.
     * @param taskSize The current Task List size.
     * @return The created task.
     */
    public Task newDeadline(String input, int taskSize) throws DukeException {
        int index = input.indexOf(" /by ");
        Task task = new Deadline(input.substring(0, index), input.substring(index + 5));
        Ui.addMessage(task, taskSize);
        return task;
    }

    /**
     * Creates a new Event task and prints a message for the user
     * confirming the addition of the task to the Task List.
     *
     * @param input The user input.
     * @param taskSize The current Task List size.
     * @return The created task.
     */
    public Task newEvent(String input, int taskSize) throws DukeException {
        int fromIndex = input.indexOf(" /from ");
        int toIndex = input.indexOf(" /to ");
        Task task = new Event(input.substring(0, fromIndex), input.substring(fromIndex + 7, toIndex),
                input.substring(toIndex + 5));
        Ui.addMessage(task, taskSize);
        return task;
    }

    /**
     * Checks if a String is an Integer.
     *
     * @param strNum The input String.
     * @return The boolean representing if the String is an Integer.
     */
    private boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }

        return true;
    }
}
