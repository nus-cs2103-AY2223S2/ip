package roody;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

import roody.commands.Command;
import roody.commands.DeleteCommand;
import roody.commands.EndCommand;
import roody.commands.FindCommand;
import roody.commands.HelpCommand;
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
        // checks for '/' input count
        String[] splitCmds = command.split("/", SPLIT_ALL);
        if (commands[0].equals("event")) {
            commands = parseEvent(splitCmds);
        } else if (commands[0].equals("deadline")) {
            commands = parseDeadline(splitCmds);
        }
        // checks formatting of commands
        checkInputFormat(commands, splitCmds);
        return createCommand(commands);
    }

    private static void checkInputFormat(String[] commands, String[] splitCmds) throws RoodyException {
        System.out.println(Arrays.toString(commands));
        switch (commands[0]) {
        case "start":
        case "list":
        case "bye":
            if (commands.length != 1) {
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
            if (commands.length != 2) {
                throw new InputFormatException(InputFormatException.CMD_INDEX);
            }
            try {
                Integer.parseInt(commands[1]);
            } catch (NumberFormatException e) {
                throw new InputFormatException(InputFormatException.CMD_INDEX);
            }
            break;
        case "find":
            if (commands.length != 2) {
                throw new InputFormatException(InputFormatException.CMD_KEY);
            }
            break;
        case "help":
            // nothing since help can have multiple inputs
            break;
        default:
            throw new RoodyException("I don't quite understand that. Type \"help\" for assistance!");
        }

    }

    private static Command createCommand(String[] inputs) throws RoodyException {
        switch (inputs[0]) {
        case "start":
            return new StartCommand();
        case "list":
            return new ListCommand();
        case "bye":
            return new EndCommand();
        case "todo":
            return new MakeTodoCommand(inputs[1]);
        case "deadline":
            return new MakeDeadlineCommand(inputs[1], LocalDate.parse(inputs[2]));
        case "event":
            return new MakeEventCommand(inputs[1], LocalDate.parse(inputs[2]), LocalDate.parse(inputs[3]));
        case "delete":
            return new DeleteCommand(Integer.parseInt(inputs[1]) - 1);
        case "mark":
            return new MarkCommand(Integer.parseInt(inputs[1]) - 1);
        case "unmark":
            return new UnmarkCommand(Integer.parseInt(inputs[1]) - 1);
        case "find":
            return new FindCommand(inputs[1]);
        case "help":
            if (inputs.length == 1) {
                return new HelpCommand("start");
            } else {
                return new HelpCommand(inputs[1]);
            }
        default:
            throw new RoodyException("Error in Parser");
        }
    }

    private static String[] parseEvent(String[] splitCmd) throws RoodyException {
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

    private static String[] parseDeadline(String[] splitCmd) throws RoodyException {
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
