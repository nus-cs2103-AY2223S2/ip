package utils;

import commands.*;
import exception.InvalidCommandException;
import exception.TaskFactoryException;
import tasks.TaskFactory;

public class Parser {

    private TaskFactory taskFactory;

    public Parser(TaskFactory taskFactory) {
        this.taskFactory = taskFactory;
    }

    public Command parse(String fullCommand) throws InvalidCommandException, TaskFactoryException {
        String[] splitStr = fullCommand.split("\\s+", 2);
        String command = splitStr[0];

        switch (command) {
        case "list":
            return new ListCommand();
        case "todo":
        case "deadline":
        case "event":
            return new AddCommand(this.taskFactory.make(fullCommand));
        case "delete":
            return new DeleteCommand(Integer.parseInt(splitStr[1]));
        case "mark":
            return new ToggleMarkTaskCommand(Integer.parseInt(splitStr[1]), true);
        case "unmark":
            return new ToggleMarkTaskCommand(Integer.parseInt(splitStr[1]), false);
        default:
            throw new InvalidCommandException("Unknown command");
        }




    }
}
