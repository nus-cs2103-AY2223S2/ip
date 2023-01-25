package duke.parser;

import duke.commands.*;
import duke.data.MyData;
import duke.exceptions.DukeException;
import duke.ui.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

public class Parser {
    /** Data object containing list of tasks. */
    private final MyData data;

    /**
     * Constructs a Parser object.
     *
     * @param data Data object containing list of tasks.
     */
    public Parser(MyData data) {
        this.data = data;
    }

    /**
     * Parses bye command.
     *
     * @return Bye object.
     */
    public Command parseBye() {
        return new Bye();
    }

    /**
     * Parses list command.
     *
     * @return List object.
     */
    public Command parseList() {
        return new List();
    }

    /**
     * Parses listdate command.
     *
     * @param commandArr Date of tasks to be displayed.
     * @return ListDate object.
     * @throws DukeException If no date given or if invalid date given.
     */
    public Command parseListDate(String[] commandArr) throws DukeException{
        if (commandArr.length <= 1) {
            throw new DukeException(Ui.wrapLines("Please enter a date e.g. listdate 'yyyy-MM-dd'"));
        }
        try {
            LocalDate date = LocalDate.parse(commandArr[1]);
            return new ListDate(date);
        } catch (DateTimeParseException e) {
            throw new DukeException(Ui.wrapLines("Please enter a date e.g. listdate 'yyyy-MM-dd'"));
        }
    }

    /**
     * Parses mark command.
     *
     * @param commandArr Index to be marked.
     * @return Mark object.
     * @throws DukeException If index not given or index not in valid range.
     */
    public Command parseMark(String[] commandArr) throws DukeException {
        if (commandArr.length <= 1) {
            throw new DukeException(Ui.wrapLines("Please enter a index to mark."));
        }
        int id = Integer.parseInt(commandArr[1]);
        if (id > data.len() || id < 0) {
            throw new DukeException(Ui.wrapLines("Please enter a valid number."));
        }
        return new Mark(id - 1);
    }

    /**
     * Parses unmark command.
     *
     * @param commandArr Index to be un-marked.
     * @return Unmark object.
     * @throws DukeException If index not given or index not in valid range.
     */
    public Command parseUnmark(String[] commandArr) throws DukeException {
        if (commandArr.length <= 1) {
            throw new DukeException(Ui.wrapLines("Please enter a index to unmark."));
        }
        int id = Integer.parseInt(commandArr[1]);
        if (id > data.len() || id < 0) {
            throw new DukeException(Ui.wrapLines("Please enter a valid number."));
        }
        return new Unmark(id - 1);
    }

    /**
     * Parses to do command.
     *
     * @param command Command entered by the user.
     * @return Add To Do object.
     * @throws DukeException If no description given.
     */
    public Command parseTodo(String command) throws DukeException {
        String description = removeCommand(command);
        if (description.isEmpty()) {
            throw new DukeException(Ui.wrapLines("Cannot have an empty task."));
        }
        return new AddToDo(description);
    }

    /**
     * Parses deadline command.
     *
     * @param slashed Array of description and due date.
     * @return AddDeadline object.
     * @throws DukeException If no description, due date or invalid format of date given.
     */
    public Command parseDeadline(String[] slashed) throws DukeException {
        if (slashed.length != 2 || removeCommand(slashed[0]).isEmpty() || removeCommand(slashed[1]).isEmpty()) {
            throw new DukeException(Ui.wrapLines("Invalid format.\n    e.g. deadline 'description' / 'yyyy-MM-dd HH-mm'"));
        }
        try {
            return new AddDeadline(removeCommand(slashed[0]), removeCommand(slashed[1]));
        } catch (DateTimeParseException e) {
            throw new DukeException(Ui.wrapLines("Invalid format.\n    e.g. deadline 'description' / 'yyyy-MM-dd HH-mm'"));
        }
    }

    /**
     * Parses event command.
     *
     * @param slashed Array of description, from date and to date.
     * @return AddEvent object.
     * @throws DukeException If no description, from date, to date, or invalid format of date given.
     */
    public Command parseEvent(String[] slashed) throws DukeException {
        if (slashed.length != 3
                || removeCommand(slashed[0]).isEmpty()
                || removeCommand(slashed[1]).isEmpty()
                || removeCommand(slashed[2]).isEmpty()) {
            throw new DukeException(Ui.wrapLines("Invalid format. From and To formatted as 'yyyy-MM-dd HH-mm'" +
                    "\n    e.g. event 'description' / 'From' / 'To'"));
        }
        try {
            return new AddEvent(removeCommand(slashed[0]),
                    removeCommand(slashed[1]),
                    removeCommand(slashed[2]));
        } catch (DateTimeParseException e) {
            throw new DukeException(Ui.wrapLines("Invalid format. From and To formatted as 'yyyy-MM-dd HH-mm'" +
                    "\n    e.g. event 'description' / 'From' / 'To'"));
        }

    }

    /**
     * Parses delete command.
     *
     * @param commandArr Index of task to be deleted.
     * @return Delete object.
     * @throws DukeException If index not given or index not in valid range.
     */
    public Command parseDelete(String[] commandArr) throws DukeException {
        if (commandArr.length <= 1) {
            throw new DukeException(Ui.wrapLines("Please enter a index to delete."));
        }
        int id = Integer.parseInt(commandArr[1]);
        if (id > data.len() || id < 0) {
            throw new DukeException(Ui.wrapLines("Please enter a valid number."));
        }
        return new Delete(id - 1);
    }

    /**
     * Removes the command word from user input.
     *
     * @param command Command that user inputs.
     * @return String without the command word.
     */
    public String removeCommand(String command) {
        String[] commandArr = command.split(" ");
        String[] descriptionArr = Arrays.copyOfRange(commandArr, 1, commandArr.length);
        return String.join(" ", descriptionArr);
    }

    /**
     * Parses the user input.
     *
     * @param command Command that the user inputs.
     * @return The corresponding command that is parsed.
     * @throws DukeException If any errors related to formatting and indexing occurs.
     */
    public Command parse(String command) throws DukeException {
        String[] commandArr = command.split(" ");
        String[] slashed = command.split("/");
        switch (commandArr[0]) {
            case "bye":
                return parseBye();
            case "list":
                return parseList();
            case "listdate":
                return parseListDate(commandArr);
            case "mark":
                return parseMark(commandArr);
            case "unmark":
                return parseUnmark(commandArr);
            case "todo":
                return parseTodo(command);
            case "deadline":
                return parseDeadline(slashed);
            case "event":
                return parseEvent(slashed);
            case "delete":
                return parseDelete(commandArr);
            default:
                throw new DukeException(Ui.wrapLines("I am not sure what that means."));
        }
    }
}
