package page;

import page.command.Command;
import page.command.CommandBye;
import page.command.CommandComplete;
import page.command.CommandDeadline;
import page.command.CommandDelete;
import page.command.CommandEvent;
import page.command.CommandFind;
import page.command.CommandHelp;
import page.command.CommandIncomplete;
import page.command.CommandLog;
import page.command.CommandTodo;


/**
 * Represents a parser that converts text inputs to Page into commands that can be executed.
 */
public class Parser {

    /**
     * Parses the input given to Page into a command to be executed.
     *
     * @param input Input given to Page.
     * @return The command described by the input.
     * @throws PageException If input is invalid.
     */
    public Command parse(String input) throws PageException {
        switch (input) {
        case "bye":
            return new CommandBye();
        case "log":
            return new CommandLog();
        case "help":
            return new CommandHelp();
        default:
            String[] splitInput = input.split(" ", 2);
            assert splitInput.length != 0 : "splitInput should have at least 1 element.";
            String firstWord = splitInput[0];
            switch (firstWord) {
            case "todo":
                return parseTodo(input);
            case "deadline":
                return parseDeadline(input);
            case "event":
                return parseEvent(input);
            case "complete":
                return parseComplete(input);
            case "incomplete":
                return parseIncomplete(input);
            case "delete":
                return parseDelete(input);
            case "find":
                return parseFind(input);
            default:
                throw new PageException("Sorry, that is not a valid input. Type 'help' to learn the valid commands.");
            }
        }
    }

    private CommandTodo parseTodo(String input) throws PageException {
        String[] splitInput = input.split(" ", 2);
        if (hasNoSplit(splitInput)) {
            throw new PageException("Sorry, please include a task description!");
        } else {
            String restOfInput = splitInput[1];
            return new CommandTodo(restOfInput);
        }
    }

    private CommandDeadline parseDeadline(String input) throws PageException {
        String[] splitInput = input.split(" ", 2);
        if (hasNoSplit(splitInput)) {
            throw new PageException("Sorry, please include a task description!");
        }

        String[] splitWithBy = splitInput[1].split(" /by ", 2);
        if (hasNoSplit(splitWithBy)) {
            throw new PageException("Sorry, please include a /by time after the quest description!");
        } else {
            String description = splitWithBy[0];
            String byDateTime = splitWithBy[1];
            return new CommandDeadline(description, byDateTime);
        }
    }

    private CommandEvent parseEvent(String input) throws PageException {
        String[] splitInput = input.split(" ", 2);
        if (hasNoSplit(splitInput)) {
            throw new PageException("Sorry, please include a task description!");
        }

        String[] splitWithFrom = splitInput[1].split(" /from ", 2);
        if (hasNoSplit(splitWithFrom)) {
            throw new PageException("Sorry, please include a /from time after the quest description!");
        }

        String[] splitWithTo = splitWithFrom[1].split(" /to ", 2);
        if (hasNoSplit(splitWithTo)) {
            throw new PageException("Sorry, please include a /to time after the /from time!");
        }
        else {
            String description = splitWithFrom[0];
            String fromDateTime = splitWithTo[0];
            String toDateTime = splitWithTo[1];
            return new CommandEvent(description, fromDateTime, toDateTime);
        }
    }

    private CommandComplete parseComplete(String input) throws PageException {
        String[] splitInput = input.split(" ", 2);
        if (hasNoSplit(splitInput)) {
            throw new PageException("Sorry, please include the index of the quest to be marked complete!");
        }

        try {
            int index = Integer.parseInt(splitInput[1]);
            return new CommandComplete(index);
        } catch (NumberFormatException e) {
            throw new PageException("Sorry, that's not a number!");
        }
    }

    private CommandIncomplete parseIncomplete(String input) throws PageException {
        String[] splitInput = input.split(" ", 2);
        if (hasNoSplit(splitInput)) {
            throw new PageException("Sorry, please include the index of the quest to be marked incomplete!");
        }

        try {
            int index = Integer.parseInt(splitInput[1]);
            return new CommandIncomplete(index);
        } catch (NumberFormatException e) {
            throw new PageException("Sorry, that's not a number!");
        }
    }

    private CommandDelete parseDelete(String input) throws PageException {
        String[] splitInput = input.split(" ", 2);
        if (hasNoSplit(splitInput)) {
            throw new PageException("Sorry, please include the index of the quest to be deleted!");
        }

        try {
            int index = Integer.parseInt(splitInput[1]);
            return new CommandDelete(index);
        } catch (NumberFormatException e) {
            throw new PageException("Sorry, that's not a number!");
        }
    }

    private CommandFind parseFind(String input) throws PageException {
        String[] splitInput = input.split(" ", 2);
        if (hasNoSplit(splitInput)) {
            throw new PageException("Sorry, please include a keyword to search for!");
        } else {
            String keyword = splitInput[1];
            return new CommandFind(keyword);
        }
    }

    private boolean hasNoSplit(String[] arr) {
        return arr.length <= 1;
    }
}

