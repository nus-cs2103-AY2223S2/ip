package roody;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import roody.commands.Command;
import roody.commands.DeleteCommand;
import roody.commands.EndCommand;
import roody.commands.FindCommand;
import roody.commands.ListCommand;
import roody.commands.MakeDeadlineCommand;
import roody.commands.MakeEventCommand;
import roody.commands.MakeTodoCommand;
import roody.commands.MarkCommand;
import roody.commands.StartCommand;
import roody.commands.UnmarkCommand;
import roody.exceptions.DateFormatException;
import roody.exceptions.InputFormatException;
import roody.exceptions.RoodyException;

/**
 * Represents a parser to take in user input.
 */
public class Parser {
    static final int SPLIT_ONCE = 2;
    static final int SPLIT_ALL = 0;

    public Parser() {}

    /**
     * Returns a array of strings with commands that are extracted from user input.
     * @param command Input string.
     * @return Commands.
     * @throws RoodyException If faulty input is detected.
     */
    public static Command parse(String command) throws RoodyException {
        // splits once by whitespace to filter by first input
        String[] commands = command.toLowerCase().split("\\s", SPLIT_ONCE);
        if (commands[0].equals("event")) {
            commands = parseEvent(command);
        } else if (commands[0].equals("deadline")) {
            commands = parseDeadline(command);
        }
        // checks for overall input length
        String[] formatCommands = command.toLowerCase().split("\\s", SPLIT_ALL);
        // checks for '/' input count
        String[] splitCmds = command.split("/", SPLIT_ALL);
        // checks formatting of commands
        switch (formatCommands[0]) {
        case "start":
        case "list":
        case "bye":
            if (formatCommands.length != 1) {
                throw new InputFormatException(InputFormatException.CMD);
            }
            break;
        case "todo":
            if (splitCmds.length != 1) {
                throw new InputFormatException(InputFormatException.CMD_TODO);
            }
            break;
        case "deadline":
            if (splitCmds.length != 2) {
                throw new InputFormatException(InputFormatException.CMD_DEADLINE);
            }
            try {
                LocalDate.parse(commands[2]);
            } catch (DateTimeParseException e) {
                throw new DateFormatException();
            }
            break;
        case "event":
            if (splitCmds.length != 3) {
                throw new InputFormatException(InputFormatException.CMD_EVENT);
            }
            try {
                LocalDate.parse(commands[2]);
                LocalDate.parse(commands[3]);
            } catch (DateTimeParseException e) {
                throw new DateFormatException();
            }
            break;
        case "delete":
        case "mark":
        case "unmark":
            if (formatCommands.length != 2) {
                throw new InputFormatException(InputFormatException.CMD_INDEX);
            }
            try {
                Integer.parseInt(commands[1]);
            } catch (NumberFormatException e) {
                throw new InputFormatException(InputFormatException.CMD_INDEX);
            }
            break;
        case "find":
            if (formatCommands.length != 2) {
                throw new InputFormatException(InputFormatException.CMD_KEY);
            }
            break;
        default:
            throw new RoodyException("I don't quite understand that.");
        }
        // create command
        switch (commands[0]) {
        case "start":
            return new StartCommand();
        case "list":
            return new ListCommand();
        case "bye":
            return new EndCommand();
        case "todo":
            return new MakeTodoCommand(commands[1]);
        case "deadline":
            return new MakeDeadlineCommand(commands[1], LocalDate.parse(commands[2]));
        case "event":
            return new MakeEventCommand(commands[1], LocalDate.parse(commands[2]), LocalDate.parse(commands[3]));
        case "delete":
            return new DeleteCommand(Integer.parseInt(commands[1]) - 1);
        case "mark":
            return new MarkCommand(Integer.parseInt(commands[1]) - 1);
        case "unmark":
            return new UnmarkCommand(Integer.parseInt(commands[1]) - 1);
        case "find":
            return new FindCommand(commands[1]);
        default:
            throw new RoodyException("Error in Parser");
        }
    }

    private static String[] parseEvent(String command) throws RoodyException {
        // split by /
        String[] splitCmd = command.split("/", SPLIT_ALL);

        // if wrong number of inputs or wrong input flag
        boolean isWrongFlag = !(splitCmd[1].substring(0, 4).equals("from") && splitCmd[1].substring(0, 2).equals("to"));
        if (splitCmd.length != 3 || isWrongFlag) {
            throw new RoodyException("No date was detected! - /from {date} /to {date}");
        }

        // maximum length should be 3
        assert splitCmd.length <= 3 : "Error while parsing, too many inputs";

        // seperates command from description
        String[] commands = new String[splitCmd.length + 1];
        for (int i = 1; i < splitCmd.length; i++) {
            commands[i + 1] = splitCmd[i];
        }
        String[] cmdAndDesc = splitCmd[0].split("\\s", SPLIT_ONCE);
        commands[0] = cmdAndDesc[0];
        commands[1] = cmdAndDesc[1];

        // seperates additional inputs
        commands[2] = splitCmd[1].split("\\s", SPLIT_ALL)[1];
        commands[3] = splitCmd[2].split("\\s", SPLIT_ALL)[1];

        return commands;
    }

    private static String[] parseDeadline(String command) throws RoodyException {
        // split by /
        String[] splitCmd = command.split("/", SPLIT_ALL);

        // if wrong number of inputs or wrong input flag
        boolean isWrongFlag = !splitCmd[1].substring(0, 2).equals("by");
        if (splitCmd.length != 2 || isWrongFlag) {
            throw new RoodyException("No date was detected! - /by {date}");
        }

        // maximum length should be 3
        assert splitCmd.length <= 2 : "Error while parsing, too many inputs";

        // seperates command from description
        String[] commands = new String[splitCmd.length + 1];
        for (int i = 1; i < splitCmd.length; i++) {
            commands[i + 1] = splitCmd[i];
        }
        String[] cmdAndDesc = splitCmd[0].split("\\s", SPLIT_ONCE);
        commands[0] = cmdAndDesc[0];
        commands[1] = cmdAndDesc[1];

        // seperates additional inputs
        commands[2] = splitCmd[1].split("\\s", SPLIT_ALL)[1];

        return commands;
    }
}
