package parser;

import commands.*;
import data.MyData;
import exceptions.DukeException;
import ui.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

public class Parser {
    private final MyData data;

    public Parser(MyData data) {
        this.data = data;
    }

    public Command parseBye() {
        return new Bye();
    }

    public Command parseList() {
        return new List();
    }

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

    public Command parseMark(String[] commandArr) throws DukeException {
        int id = Integer.parseInt(commandArr[1]);
        Mark toMark = new Mark(id - 1);
        if (id > data.len() || id < 0) {
            throw new DukeException(Ui.wrapLines("Please enter a valid number."));
        }
        return toMark;
    }

    public Command parseUnmark(String[] commandArr) throws DukeException {
        int id = Integer.parseInt(commandArr[1]);
        Unmark toUnmark = new Unmark(id - 1);
        if (id > data.len() || id < 0) {
            throw new DukeException(Ui.wrapLines("Please enter a valid number."));
        }
        return toUnmark;
    }

    public Command parseTodo(String command) throws DukeException {
        String description = removeCommand(command);
        if (description.isEmpty()) {
            throw new DukeException(Ui.wrapLines("Cannot have an empty task."));
        }
        return new AddToDo(description);
    }

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

    public Command parseDelete(String[] commandArr) throws DukeException {
        int id = Integer.parseInt(commandArr[1]);
        if (id > data.len() || id < 0) {
            throw new DukeException(Ui.wrapLines("Please enter a valid number."));
        }
        return new Delete(id - 1);
    }

    public String removeCommand(String command) {
        String[] commandArr = command.split(" ");
        String[] descriptionArr = Arrays.copyOfRange(commandArr, 1, commandArr.length);
        return String.join(" ", descriptionArr);
    }

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
