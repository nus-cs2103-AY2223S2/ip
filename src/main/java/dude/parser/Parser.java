package dude.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import dude.command.AddCommand;
import dude.command.Command;
import dude.command.DeleteCommand;
import dude.command.ExitCommand;
import dude.command.FindCommand;
import dude.command.ListCommand;
import dude.command.MarkCommand;
import dude.command.UndoCommand;
import dude.command.UnmarkCommand;
import dude.exception.DudeException;
import dude.task.Deadline;
import dude.task.Event;
import dude.task.Todo;

/**
 * Parses user inputs into actual commands.
 */
public class Parser {
    /**
     * Enums for different possible command types.
     */
    public enum CommandType {
        LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, FIND, UNDO, BYE
    }

    /**
     * Parses command input given by the user into an actual command.
     * If command is not valid, DudeException is thrown.
     *
     * @param command Command input provided by user.
     * @return Command object.
     * @throws DudeException  If command is not valid.
     */
    public static Command parse(String command) throws DudeException {
        String[] cmd = command.split(" ", 2);
        String[] format;

        CommandType commandType;

        try {
            commandType = CommandType.valueOf(cmd[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new DudeException("Uhh... You give me wrong command leh");
        }

        validate(commandType, command);

        switch (commandType) {
        case LIST:
            return new ListCommand();
        case BYE:
            return new ExitCommand();
        case MARK:
            return new MarkCommand(Integer.parseInt(cmd[1]));
        case UNMARK:
            return new UnmarkCommand(Integer.parseInt(cmd[1]));
        case TODO:
            return new AddCommand(new Todo(cmd[1]));
        case DEADLINE:
            format = cmd[1].split(" /by ");
            return new AddCommand(new Deadline(format[0], format[1]));
        case EVENT:
            format = cmd[1].split(" /from ");
            String[] details = format[1].split(" /to ");
            return new AddCommand(new Event(format[0], details[0], details[1]));
        case DELETE:
            return new DeleteCommand(Integer.parseInt(cmd[1]));
        case FIND:
            return new FindCommand(cmd[1].split(" "));
        case UNDO:
            return new UndoCommand();
        default:
            throw new DudeException("Uhh... You give me wrong command leh");
        }
    }

    /**
     * Validates command input given by user.
     * If command is not valid, DudeException is thrown.
     *
     * @param commandType Type of command.
     * @param command Command input provided by user.
     * @throws DudeException If command is not valid.
     */
    public static void validate(CommandType commandType, String command) throws DudeException {
        String[] cmd = command.split(" ", 2);
        String[] input = command.split(" ");
        String[] format;

        switch (commandType) {
        case LIST:
        case BYE:
        case UNDO:
            if (cmd.length != 1) {
                throw new DudeException("Uhh... I think you added extra commands?");
            }
            break;
        case MARK:
        case UNMARK:
        case DELETE:
            if (input.length > 2) {
                throw new DudeException("Uhh... I think you added extra commands leh");
            } else if (input.length < 2) {
                throw new DudeException("Uhh... You also need to put the task after the command");
            } else {
                try {
                    Integer.parseInt(cmd[1]);
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    throw new DudeException("Uhh... You need to use a valid task number also");
                }
            }
            break;
        case TODO:
            if (cmd.length < 2) {
                throw new DudeException("Uhh...  You need to type the description also");
            }
            break;
        case DEADLINE:
            if (input.length < 2 || !cmd[1].contains("/by")) {
                throw new DudeException("Uhh...  You need to use /by to indicate the date");
            } else if (input[1].equals("/by")) {
                throw new DudeException("Uhh...  You need to include the description also");
            } else if (input[input.length - 1].equals("/by")) {
                throw new DudeException("Uhh...  You need to include the date also");
            }

            format = cmd[1].split(" /by ");
            try {
                LocalDateTime.parse(format[1], DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            } catch (DateTimeParseException e) {
                throw new DudeException("Uhh... You using wrong format of date leh (YYYY-MM-DD HHmm)");
            }
            break;
        case EVENT:
            if (input.length < 2 || !cmd[1].contains("/from") || !cmd[1].contains("/to")) {
                throw new DudeException("Uhh...  You need to use /from and /to to indicate the dates for the event");
            } else if (input[1].equals("/from") || input[1].equals("/to")) {
                throw new DudeException("Uhh...  You need to include the description also");
            } else if (input[input.length - 1].equals("/from")
                    || input[input.length - 1].equals("/to")) {
                throw new DudeException("Uhh... Please include the dates and ensure it is in the valid format");
            }

            format = cmd[1].split(" /from ");
            String[] details = format[1].split(" /to ");

            try {
                LocalDateTime startDate = LocalDateTime.parse(details[0],
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
                LocalDateTime endDate = LocalDateTime.parse(details[1],
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));

                if (endDate.isBefore(startDate) || startDate.isEqual(endDate)) {
                    throw new DudeException("Uhh... Your start date must be earlier than your end date");
                }
            } catch (DateTimeParseException e) {
                throw new DudeException("Uhh... You using wrong format of date leh (YYYY-MM-DD HHmm)");
            }
            break;
        case FIND:
            if (input.length < 2) {
                throw new DudeException("Uhh... You also need to put the keyword after the command");
            }
            break;
        default:
            throw new DudeException("Uhh...  This one invalid command leh");
        }
    }
}
