package duke.parser;

import duke.commands.AddCommand;
import duke.commands.ChangeStatusCommand;
import duke.commands.Command;
import duke.commands.DateCommand;
import duke.commands.DeleteCommand;
import duke.commands.ExitCommand;
import duke.commands.IncorrectCommand;
import duke.commands.ListCommand;

public class Parser {

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
        default:
            return new IncorrectCommand("invalid");
        }
    }

    public static Command prepareAddCommand(String commandType, String fullCommand) {
        //command type removed from full command
        try {
            String temp = fullCommand.split(" ", 2)[1];

            if (commandType.equals("todo")) {
                return new AddCommand(temp);
            } else if (commandType.equals("deadline")) {
                //separate description and dateTime
                String[] details = temp.split("/by ");
                return new AddCommand(details[0], details[1]);
            } else if (commandType.equals("event")) {
                //command type is event
                //separate description and event start end
                String[] details = temp.split("/from ", 2);
                //separate start and end dateTime
                String[] time = details[1].split("/to ");
                return new AddCommand(details[0], time[0], time[1]);
            } else {
                return new IncorrectCommand("task");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return new IncorrectCommand("task");
        }
    }

    public static Command prepareChangeStatusCommand(String changeType, String fullCommand) {
        try {
            int taskNumber = Integer.parseInt(fullCommand.split(" ")[1]) - 1;
            return new ChangeStatusCommand(changeType, taskNumber);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException a) {
            return new IncorrectCommand(changeType);
        }
    }

    public static Command prepareDateCommand(String fullCommand) {
        try {
            String date = fullCommand.split(" ", 2)[1];
            return new DateCommand(date);
        } catch (ArrayIndexOutOfBoundsException e) {
            return new IncorrectCommand("date");
        }


    }
    public static Command prepareDeleteCommand(String fullCommand) {
        try {
            int taskNumber = Integer.parseInt(fullCommand.split(" ")[1]) - 1;
            return new DeleteCommand(taskNumber);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException a) {
            return new IncorrectCommand("delete");
        }
    }
}

