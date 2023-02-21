package duke.parser;

import duke.commands.AddCommand;
import duke.commands.ChangeStatusCommand;
import duke.commands.Command;
import duke.commands.DateCommand;
import duke.commands.DeleteCommand;
import duke.commands.ExitCommand;
import duke.commands.FindCommand;
import duke.commands.IncorrectCommand;
import duke.commands.ListCommand;
import duke.tasks.MyDateTime;

/**
 * Represents a parser that receives and parses user input.
 */
public class Parser {

    /**
     * Generates the correct command according to user command.
     * @param fullCommand the user's full input.
     * @return the corresponding command.
     */
    public static Command parse(String fullCommand) {
        String commandType = fullCommand.split(" ")[0].toLowerCase();

        switch (commandType) {
        case "list":
            return new ListCommand();
        case "todo":
        case "deadline":
        case "event":
            return prepareAddCommand(commandType, fullCommand);
        case "mark":
        case "unmark":
            return prepareChangeStatusCommand(commandType, fullCommand);
        case "delete":
            return prepareDeleteCommand(fullCommand);
        case "date":
            return prepareDateCommand(fullCommand);
        case "bye":
            return new ExitCommand();
        case "find":
            return prepareFindCommand(commandType, fullCommand);
        default:
            return new IncorrectCommand("invalid");
        }
    }


    /**
     * Prepares user input for a find command.
     * @param commandType find.
     * @param fullCommand The user's full input.
     * @return A command of type find or incorrect.
     */
    public static Command prepareFindCommand(String commandType, String fullCommand) {
        try {
            String toFind = fullCommand.split(" ", 2)[1];
            return new FindCommand(toFind);
        } catch (ArrayIndexOutOfBoundsException a) {
            return new IncorrectCommand(commandType);
        }
    }

    /**
     * Prepares the user input for the add command.
     * @param commandType to do, deadline or event.
     * @param fullCommand the user's full input.
     * @return a command of type add or incorrect.
     */
    public static Command prepareAddCommand(String commandType, String fullCommand) {
        //command type removed from full command
        try {
            String temp = fullCommand.split(" ", 2)[1];

            if (commandType.equals("todo")) {
                return new AddCommand(temp);
            } else if (commandType.equals("deadline")) {
                //separate description and dateTime
                String[] details = temp.split("/by");
                if (!MyDateTime.isValidDateTime(details[1])) {
                    return new IncorrectCommand("dt format");
                }
                return new AddCommand(details[0], details[1]);
            } else if (commandType.equals("event")) {
                //separate description and event start end
                String[] details = temp.split(" /from ", 2);
                //separate start and end dateTime
                String[] time = details[1].split(" /to ");
                if (!MyDateTime.isValidDateTime(time[0]) || !MyDateTime.isValidDateTime(time[1])) {
                    return new IncorrectCommand("dt format");
                }
                return new AddCommand(details[0], time[0], time[1]);
            } else {
                return new IncorrectCommand("task");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return new IncorrectCommand("task");
        }
    }

    /**
     * Prepares the user input for the add command.
     * @param changeType mark or unmark.
     * @param fullCommand the user's full input.
     * @return a command of type change status or incorrect.
     */
    public static Command prepareChangeStatusCommand(String changeType, String fullCommand) {
        try {
            int taskNumber = Integer.parseInt(fullCommand.split(" ")[1]) - 1;
            return new ChangeStatusCommand(changeType, taskNumber);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException a) {
            return new IncorrectCommand(changeType);
        }
    }

    /**
     * Prepares the user input for the date command.
     * @param fullCommand the user's full input.
     * @return a command of type date or incorrect.
     */
    public static Command prepareDateCommand(String fullCommand) {
        try {
            String date = fullCommand.split(" ", 2)[1];
            return new DateCommand(date);
        } catch (ArrayIndexOutOfBoundsException e) {
            return new IncorrectCommand("date");
        }
    }

    /**
     * Prepares the user input for the delete command.
     * @param fullCommand the user's full input.
     * @return a command of type delete or incorrect.
     */
    public static Command prepareDeleteCommand(String fullCommand) {
        try {
            int taskNumber = Integer.parseInt(fullCommand.split(" ")[1]) - 1;
            return new DeleteCommand(taskNumber);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException a) {
            return new IncorrectCommand("delete");
        }
    }
}

