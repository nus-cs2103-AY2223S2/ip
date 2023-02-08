package roody;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

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
    public static String[] parse(String command) throws RoodyException {
        // splits once by whitespace to filter by first input
        String[] commands = command.toLowerCase().split("\\s", SPLIT_ONCE);
        if (commands[0].equals("event")) {
            commands = parseEvent(command);
        } else if (commands[0].equals("deadline")) {
            commands = parseDeadline(command);
        }
        String[] formatCommands = command.toLowerCase().split("\\s", SPLIT_ALL);
        String[] splitCmds = command.split("/", SPLIT_ALL);
        // checks formatting of remaining commands
        switch (formatCommands[0]) {
        case "start":
        case "list":
        case "bye":
            if (formatCommands.length != 1) {
                throw new InputFormatException("I expected \"{Command}\" but got something else");
            }
            break;
        case "todo":
            if (splitCmds.length != 1) {
                throw new InputFormatException("I expected \"{Command} {Desc}\" but got something else");
            }
            break;
        case "deadline":
            if (splitCmds.length != 2) {
                throw new InputFormatException("I expected \"{Command} {Desc} /by {DateTime}\" but got something else");
            }
            try {
                // checks the expected date if it matches the correct format
                LocalDate.parse(commands[2]);
            } catch (DateTimeParseException e) {
                throw new DateFormatException();
            }
            break;
        case "event":
            if (splitCmds.length != 3) {
                throw new InputFormatException("I expected \"{Command} {Desc} /from {DateTime} /to {DateTime}\" "
                        + "but got something else");
            }
            try {
                // checks the expected date if it matches the correct format
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
                throw new InputFormatException("I expected \"{Command} {Index}\" but got something else");
            }
            try {
                Integer.parseInt(commands[1]);
            } catch (NumberFormatException e) {
                throw new InputFormatException("I expected \"{Command} {Index}\" but got something else");
            }
            break;
        case "find":
            if (formatCommands.length != 2) {
                throw new InputFormatException("I expected \"{Command} {Keyword}\" but got something else");
            }
            break;
        default:
            throw new RoodyException("I don't quite understand that.");
        }
        for (int i = 0; i < commands.length; i++) {
            commands[i] = commands[i].trim();
        }
        return commands;
    }

    private static String[] parseEvent(String command) throws RoodyException {
        String[] commands = command.toLowerCase().split("\\s", SPLIT_ONCE);
        // split by /
        String[] splitCmd = command.split("/", SPLIT_ALL);

        // if wrong number of inputs or wrong input flag
        boolean isWrongFlag = !(splitCmd[1].substring(0, 4).equals("from") && splitCmd[1].substring(0, 2).equals("to"));
        if (splitCmd.length != 3 || isWrongFlag) {
            throw new RoodyException("No date was detected! - /from {date} /to {date}");
        }

        // seperates command from description
        commands = new String[splitCmd.length + 1];
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
        String[] commands = command.toLowerCase().split("\\s", SPLIT_ONCE);
        // split by /
        String[] splitCmd = command.split("/", SPLIT_ALL);

        // if wrong number of inputs or wrong input flag
        boolean isWrongFlag = !splitCmd[1].substring(0, 2).equals("by");
        if (splitCmd.length != 2 || isWrongFlag) {
            throw new RoodyException("No date was detected! - /by {date}");
        }

        // seperates command from description
        commands = new String[splitCmd.length + 1];
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
