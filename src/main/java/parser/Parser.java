package parser;

import commands.*;
import data.MyData;
import exceptions.DukeException;
import ui.Ui;
import java.util.Arrays;

public class Parser {
    private final MyData data = new MyData();

    public void parseUnknown() throws DukeException {
        throw new DukeException(Ui.wrapLines("I am not sure what that means."));
    }

    public void parseBye() {
        Bye exit = new Bye();
        exit.execute(data);
    }

    public void parseList(String[] commandArr) {
        List toList = new List();
        toList.execute(data);
    }

    public void parseMark(String[] commandArr) throws DukeException {
        int id = Integer.parseInt(commandArr[1]);
        Mark toMark = new Mark(id - 1);
        if (id > data.len() || id < 0) {
            throw new DukeException(Ui.wrapLines("Please enter a valid number."));
        }
        toMark.execute(data);
    }

    public void parseUnmark(String[] commandArr) throws DukeException {
        int id = Integer.parseInt(commandArr[1]);
        Unmark toUnmark = new Unmark(id - 1);
        if (id > data.len() || id < 0) {
            throw new DukeException(Ui.wrapLines("Please enter a valid number."));
        }
        toUnmark.execute(data);
    }

    public void parseTodo(String command) throws DukeException {
        String description = removeCommand(command);
        if (description.isEmpty()) {
            throw new DukeException(Ui.wrapLines("Cannot have an empty task."));
        }
        AddToDo toAdd = new AddToDo(description);
        toAdd.execute(data);
    }

    public void parseDeadline(String[] slashed) throws DukeException {
        if (slashed.length != 2 || removeCommand(slashed[0]).isEmpty() || removeCommand(slashed[1]).isEmpty()) {
            throw new DukeException(Ui.wrapLines("Invalid format. e.g. deadline 'description' / 'due date'"));
        }
        AddDeadline toAdd = new AddDeadline(removeCommand(slashed[0]), removeCommand(slashed[1]));
        toAdd.execute(data);
    }

    public void parseEvent(String[] slashed) throws DukeException {
        if (slashed.length != 3
                || removeCommand(slashed[0]).isEmpty()
                || removeCommand(slashed[1]).isEmpty()
                || removeCommand(slashed[2]).isEmpty()) {
            throw new DukeException(Ui.wrapLines("Invalid format.\n    e.g. event 'description' / 'time' / 'time'"));
        }
        AddEvent toAdd = new AddEvent(removeCommand(slashed[0]),
                removeCommand(slashed[1]),
                removeCommand(slashed[2]));
        toAdd.execute(data);
    }

    public String removeCommand(String command) {
        String[] commandArr = command.split(" ");
        String[] descriptionArr = Arrays.copyOfRange(commandArr, 1, commandArr.length);
        return String.join(" ", descriptionArr);
    }

    public void parse(String command) {
        String[] commandArr = command.split(" ");
        String[] slashed = command.split("/");
        switch (commandArr[0]) {
            case "bye":
                parseBye();
                break;
            case "list":
                parseList(commandArr);
                break;
            case "mark":
                try {
                    parseMark(commandArr);
                } catch (DukeException e){
                    System.out.print(e.getMessage());
                }
                break;
            case "unmark":
                try {
                    parseUnmark(commandArr);
                } catch (DukeException e) {
                    System.out.print(e.getMessage());
                }
                break;
            case "todo":
                try {
                    parseTodo(command);
                } catch (DukeException e) {
                    System.out.print(e.getMessage());
                }
                break;
            case "deadline":
                try {
                    parseDeadline(slashed);
                } catch (DukeException e) {
                    System.out.print(e.getMessage());
                }
                break;
            case "event":
                try {
                    parseEvent(slashed);
                } catch (DukeException e) {
                    System.out.print(e.getMessage());
                }
                break;
            default:
                try {
                    parseUnknown();
                } catch (DukeException e) {
                    System.out.print(e.getMessage());
                }
                break;
        }
    }
}
