package parser;

import commands.*;
import data.MyData;
import exceptions.DukeException;
import ui.Ui;
import java.util.Arrays;

public class Parser {
    private final MyData data;

    public Parser(MyData data) {
        this.data = data;
    }

    public Command parseBye() {
        return new Bye();
    }

    public Command parseList(String[] commandArr) {
        return new List();
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
            throw new DukeException(Ui.wrapLines("Invalid format. e.g. deadline 'description' / 'due date'"));
        }
        return new AddDeadline(removeCommand(slashed[0]), removeCommand(slashed[1]));
    }

    public Command parseEvent(String[] slashed) throws DukeException {
        if (slashed.length != 3
                || removeCommand(slashed[0]).isEmpty()
                || removeCommand(slashed[1]).isEmpty()
                || removeCommand(slashed[2]).isEmpty()) {
            throw new DukeException(Ui.wrapLines("Invalid format.\n    e.g. event 'description' / 'time' / 'time'"));
        }
        return new AddEvent(removeCommand(slashed[0]),
                removeCommand(slashed[1]),
                removeCommand(slashed[2]));
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
                return parseList(commandArr);
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
