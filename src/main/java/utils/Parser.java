package utils;

import commands.*;

import exception.InvalidCommandException;
import exception.TaskFactoryException;
import exception.TreeBotException;
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
    public Command parse(String fullCommand) throws TreeBotException {
        fullCommand += " ";
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
            try {
                return new DeleteCommand(Integer.parseInt(splitStr[1]));
            } catch (NumberFormatException e) {
                throw new TreeBotException("Valid index must be given");
            }
        case "mark":
            try {
                return new ToggleMarkTaskCommand(Integer.parseInt(splitStr[1]), true);
            } catch (NumberFormatException e) {
                throw new TreeBotException("Valid index must be given");
            }
        case "unmark":
            try {
                return new ToggleMarkTaskCommand(Integer.parseInt(splitStr[1]), false);
            } catch (NumberFormatException e) {
                throw new TreeBotException("Valid index must be given");
            }
        case "find":
            return new FindCommand(splitStr[1]);
        case "undo":
            return new UndoCommand();
        default:
            throw new InvalidCommandException("Unknown command");
        }

    }


}
