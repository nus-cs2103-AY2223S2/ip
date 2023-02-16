package utils;

import commands.*;

import exception.InvalidCommandException;
import exception.TaskFactoryException;
import tasks.TaskFactory;

/**
 * Represents a parser that parses raw user input and returns an executable Command.
 */
public class Parser {

    private TaskFactory taskFactory;

    public Parser(TaskFactory taskFactory) {
        this.taskFactory = taskFactory;
    }

    /**
     * Parses and returns a command according to the raw user input.
     * @param fullCommand
     * @return a Command according to the user input.
     * @throws InvalidCommandException
     * @throws TaskFactoryException
     */
    public Command parse(String fullCommand) throws InvalidCommandException, TaskFactoryException {
        String[] splitStr = fullCommand.split("\\s+", 2);
        String command = splitStr[0];

        switch (command) {
        case "list":
            return new ListCommand();
        case "todo":
        case "deadline":
        case "event":
            return new AddCommand(taskFactory.make(command, splitStr[1]));
        case "delete":
            return new DeleteCommand(Integer.parseInt(splitStr[1]));
        case "mark":
            return new ToggleMarkTaskCommand(Integer.parseInt(splitStr[1]), true);
        case "unmark":
            return new ToggleMarkTaskCommand(Integer.parseInt(splitStr[1]), false);
        case "find":
            return new FindCommand(splitStr[1]);
        case "undo":
            return new UndoCommand();
        default:
            throw new InvalidCommandException("Unknown command");
        }

    }
}
