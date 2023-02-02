package pix.parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

import pix.commands.AddDeadline;
import pix.commands.AddEvent;
import pix.commands.AddToDo;
import pix.commands.Bye;
import pix.commands.Command;
import pix.commands.Delete;
import pix.commands.Find;
import pix.commands.List;
import pix.commands.ListDate;
import pix.commands.Mark;
import pix.commands.Unmark;
import pix.data.MyData;
import pix.exceptions.PixException;
import pix.ui.Ui;

/**
 * Parser class to parse commands by the user.
 */
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
     * @throws PixException If no date given or if invalid date given.
     */
    public Command parseListDate(String[] commandArr) throws PixException {
        if (commandArr.length <= 1) {
            throw new PixException("Please enter a date.\n\n e.g. listdate 'yyyy-MM-dd'");
        }
        try {
            LocalDate date = LocalDate.parse(commandArr[1]);
            return new ListDate(date);
        } catch (DateTimeParseException e) {
            throw new PixException("Please enter a date.\n\n e.g. listdate 'yyyy-MM-dd'");
        }
    }

    /**
     * Parses mark command.
     *
     * @param commandArr Index to be marked.
     * @return Mark object.
     * @throws PixException If index not given or index not in valid range.
     */
    public Command parseMark(String[] commandArr) throws PixException {
        if (commandArr.length <= 1) {
            throw new PixException("Please enter an index to mark.");
        }
        try {
            int id = Integer.parseInt(commandArr[1]);
            if (id > data.len() || id < 0) {
                throw new PixException("Please enter a valid number.");
            }
            return new Mark(id - 1);
        } catch (NumberFormatException e) {
            throw new PixException(("Please enter an index to mark."));
        }
    }

    /**
     * Parses unmark command.
     *
     * @param commandArr Index to be un-marked.
     * @return Unmark object.
     * @throws PixException If index not given or index not in valid range.
     */
    public Command parseUnmark(String[] commandArr) throws PixException {
        if (commandArr.length <= 1) {
            throw new PixException("Please enter a index to unmark.");
        }
        try {
            int id = Integer.parseInt(commandArr[1]);
            if (id > data.len() || id < 0) {
                throw new PixException("Please enter a valid number.");
            }
            return new Unmark(id - 1);
        } catch (NumberFormatException e) {
            throw new PixException(("Please enter an index to mark."));
        }
    }

    /**
     * Parses to do command.
     *
     * @param command Command entered by the user.
     * @return Add To Do object.
     * @throws PixException If no description given.
     */
    public Command parseTodo(String command) throws PixException {
        String description = removeCommand(command);
        if (description.isEmpty()) {
            throw new PixException("Cannot have an empty task.");
        }
        return new AddToDo(description);
    }

    /**
     * Parses deadline command.
     *
     * @param slashed Array of description and due date.
     * @return AddDeadline object.
     * @throws PixException If no description, due date or invalid format of date given.
     */
    public Command parseDeadline(String[] slashed) throws PixException {
        if (slashed.length != 2 || removeCommand(slashed[0]).isEmpty() || removeCommand(slashed[1]).isEmpty()) {
            throw new PixException("Invalid format.\n\n"
                    + "e.g. deadline 'description' / 'yyyy-MM-dd HH-mm'");
        }
        try {
            return new AddDeadline(removeCommand(slashed[0]), removeCommand(slashed[1]));
        } catch (DateTimeParseException e) {
            throw new PixException("Invalid format.\n\n"
                    + "e.g. deadline 'description' / 'yyyy-MM-dd HH-mm'");
        }
    }

    /**
     * Parses event command.
     *
     * @param slashed Array of description, from date and to date.
     * @return AddEvent object.
     * @throws PixException If no description, from date, to date, or invalid format of date given.
     */
    public Command parseEvent(String[] slashed) throws PixException {
        if (slashed.length != 3
                || removeCommand(slashed[0]).isEmpty()
                || removeCommand(slashed[1]).isEmpty()
                || removeCommand(slashed[2]).isEmpty()) {
            throw new PixException("Invalid format.\n\nFrom and To formatted as 'yyyy-MM-dd HH-mm'"
                    + "\n\ne.g. event 'description' / 'From' / 'To'");
        }
        try {
            return new AddEvent(removeCommand(slashed[0]),
                    removeCommand(slashed[1]),
                    removeCommand(slashed[2]));
        } catch (DateTimeParseException e) {
            throw new PixException("Invalid format.\n\nFrom and To formatted as 'yyyy-MM-dd HH-mm'"
                    + "\n\ne.g. event 'description' / 'From' / 'To'");
        }

    }

    /**
     * Parses delete command.
     *
     * @param commandArr Index of task to be deleted.
     * @return Delete object.
     * @throws PixException If index not given or index not in valid range.
     */
    public Command parseDelete(String[] commandArr) throws PixException {
        if (commandArr.length <= 1) {
            throw new PixException("Please enter an index to delete.");
        }
        int id = Integer.parseInt(commandArr[1]);
        if (id > data.len() || id < 0) {
            throw new PixException("Please enter a valid number.");
        }
        return new Delete(id - 1);
    }

    /**
     * Parses find command.
     *
     * @param commandArr Keyword to seach task by.
     * @return Tasks if keyword is in the description.
     * @throws PixException If no keyword is given.
     */
    public Command parseFind(String[] commandArr) throws PixException {
        if (commandArr.length <= 1) {
            throw new PixException("Please enter a keyword");
        }
        String keyword = commandArr[1];
        return new Find(keyword);
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
     * @throws PixException If any errors related to formatting and indexing occurs.
     */
    public Command parse(String command) throws PixException {
        String[] commandArr = command.split(" ");
        String[] slashed = command.split("/");
        switch (commandArr[0]) {
        case "bye":
            return parseBye();
        case "ls":
            return parseList();
        case "lsd":
            return parseListDate(commandArr);
        case "mk":
            return parseMark(commandArr);
        case "unmk":
            return parseUnmark(commandArr);
        case "todo":
            return parseTodo(command);
        case "dline":
            return parseDeadline(slashed);
        case "event":
            return parseEvent(slashed);
        case "rm":
            return parseDelete(commandArr);
        case "find":
            return parseFind(commandArr);
        default:
            throw new PixException("I am not sure what that means.");
        }
    }
}
