package nemo;

import nemo.command.Command;
import nemo.command.Commands;
import nemo.command.DeadlineCommand;
import nemo.command.DeleteCommand;
import nemo.command.EventCommand;
import nemo.command.ExitCommand;
import nemo.command.FindCommand;
import nemo.command.ListCommand;
import nemo.command.MarkCommand;
import nemo.command.ToDoCommand;
import nemo.command.UnmarkCommand;
import nemo.exception.NemoException;


/**
 * Parses user input and returns Command.
 *
 * @author Lian Kok Hai
 */
public class Parser {
    /**
     * Parses user input and returns Command or throws
     * NemoException when invalid input encountered.
     *
     * @param userInput User's string input.
     * @return Command object to be executed.
     * @throws NemoException
     */
    public Command parse(String userInput) throws NemoException {
        Commands command = Parser.getCommand(userInput);
        Command c = null;
        switch (command) {
        case BYE:
            c = new ExitCommand();
            break;
        case LIST:
            c = new ListCommand();
            break;
        case MARK:
            int index = Parser.parseMarkOrDeleteCommands(userInput);
            c = new MarkCommand(index);
            break;
        case UNMARK:
            index = Parser.parseMarkOrDeleteCommands(userInput);
            c = new UnmarkCommand(index);
            break;
        case DELETE:
            index = Parser.parseMarkOrDeleteCommands(userInput);
            c = new DeleteCommand(index);
            break;
        case TODO:
            c = new ToDoCommand(Parser.parseTodoCommand(userInput));
            break;
        case DEADLINE:
            // parsed[0] is description of task; parsed[1] is by
            String[] parsed = Parser.parseDeadlineCommand(userInput);
            c = new DeadlineCommand(parsed[0], parsed[1]);
            break;
        case EVENT:
            parsed = Parser.parseEventCommand(userInput);
            c = new EventCommand(parsed[0], parsed[1], parsed[2]);
            break;
        case FIND:
            String toFind = Parser.parseFindCommand(userInput);
            c = new FindCommand(toFind);
            break;
        default:
        }
        return c;
    }

    private static Commands getCommand(String userInput) throws NemoException {
        try {
            String strCommand = userInput.split(" ", 2)[0];
            Commands command = Commands.valueOf(strCommand.toUpperCase());
            return command;
        } catch (IllegalArgumentException e) {
            throw new NemoException("Invalid command");
        } catch (NullPointerException e) {
            throw new NemoException("Null exception encountered");
        }
    }

    private static int parseMarkOrDeleteCommands(String userInput) throws NemoException {
        String[] splitStr = userInput.split(" ", 2);
        if (splitStr.length < 2) {
            throw new NemoException("Mark / Unmark / Delete commands require an "
                    + "integer argument referring to task number");
        }
        try {
            int taskNumber = Integer.parseInt(splitStr[1]);
            return taskNumber;
        } catch (NumberFormatException e) {
            throw new NemoException("Format of argument cannot be parsed as an integer");
        }
    }

    private static String parseTodoCommand(String userInput) throws NemoException {
        String[] splitStr = userInput.split(" ", 2);
        if (splitStr.length < 2) {
            throw new NemoException("Todo command requires a task description");
        }
        return splitStr[1];
    }

    private static String[] parseDeadlineCommand(String userInput) throws NemoException {
        String[] splitStr = userInput.split(" ", 2);
        if (splitStr.length < 2) {
            throw new NemoException("Deadline command requires task description and /by argument");
        }
        String[] output = splitStr[1].split(" /by ", 2);
        if (output.length < 2) {
            throw new NemoException("Deadline command requires task description and /by argument");
        }
        return output;
    }

    private static String[] parseEventCommand(String userInput) throws NemoException {
        String[] splitStr = userInput.split(" ", 2);
        if (splitStr.length < 2) {
            throw new NemoException("Event command requires task description, "
                    + "/from argument and /to argument");
        }
        String[] splitFrom = splitStr[1].split(" /from ", 2);
        if (splitFrom.length < 2) {
            throw new NemoException("Event command requires task description, "
                    + "/from argument and /to argument");
        }
        String[] splitTo = splitFrom[1].split(" /to ", 2);
        if (splitTo.length < 2) {
            throw new NemoException("Event command requires task description, "
                    + "/from argument and /to argument");
        }
        // [0] is description of task; [1] is from; [2] is to
        return new String[] {splitFrom[0], splitTo[0], splitTo[1]};
    }

    private static String parseFindCommand(String userInput) throws NemoException {
        String[] splitStr = userInput.split(" ", 2);
        if (splitStr.length < 2) {
            throw new NemoException("Find command requires String to base search on");
        }
        return splitStr[1];
    }
}
