package duke.parser;

import java.time.format.DateTimeParseException;
import java.util.Optional;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.constant.DukeCommand;
import duke.constant.Message;
import duke.exception.DukeException;
import duke.exception.InvalidCommandArgsException;
import duke.exception.NoSuchCommandException;
import duke.utils.DateUtil;

/**
 * Converts string inputs into duke commands.
 */
public class Parser {

    /**
     * Parses csv string "T,1,..." into an AddCommand object.
     *
     * @param input raw csv row input
     * @return {@link AddCommand} object respective command object
     * @throws DukeException          if error occurs when converting to command
     *                                object
     * @throws DateTimeParseException if error occurs when parsing input to date
     *                                object
     */
    public static AddCommand parseCsv(String input) throws DukeException, DateTimeParseException {
        String[] ops = input.split(",");

        if (ops.length < 3) {
            throw new NoSuchCommandException(Message.EXCEPTION_NOSUCH_COMMAND);
        }

        String taskType = ops[0];
        boolean isDone = ops[1].equalsIgnoreCase("1") ? true : false;
        String title = ops[2];

        switch (taskType) {
        case "T":
            if (ops.length != 3) {
                throw new InvalidCommandArgsException(Message.EXCEPTION_INVALID_TODO_CMD);
            }

            return new AddCommand(title, isDone);
        case "D":
            if (ops.length != 4) {
                throw new InvalidCommandArgsException(Message.EXCEPTION_INVALID_DATE_CMD);
            }

            return new AddCommand(title, isDone, DateUtil.toLocalDateTime(ops[3]));
        case "E":
            if (ops.length != 5) {
                throw new InvalidCommandArgsException(Message.EXCEPTION_INVALID_EVENT_CMD);
            }

            return new AddCommand(title, isDone, DateUtil.toLocalDateTime(ops[3]),
                    DateUtil.toLocalDateTime(ops[4]));
        default:
            throw new NoSuchCommandException(Message.EXCEPTION_NOSUCH_COMMAND);
        }

    }

    /**
     * Parses the raw command and trigger the respective duke actions.
     *
     * @param fullCommand raw text input
     * @return {@link Command} object respective command object
     * @throws DukeException if error occurs when converting to command
     *                       object
     */
    public static Command parse(String fullCommand) throws DukeException {
        String[] ops = fullCommand.split(" ", 2);
        DukeCommand command = null;

        try {
            command = DukeCommand.valueOf(ops[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new NoSuchCommandException(Message.EXCEPTION_NOSUCH_COMMAND);
        }
        try {
            // handle simple commands
            Optional<Command> c = handleSimpleCommand(command);
            if (!c.isEmpty()) {
                return c.get();
            }

            // handle commands with single args
            c = handleSingleArgCommand(command, ops);
            if (!c.isEmpty()) {
                return c.get();
            }

            // handle commands with multiple args
            c = handleComplexArgCommand(command, ops);
            if (!c.isEmpty()) {
                return c.get();
            } else {
                throw new NoSuchCommandException(Message.EXCEPTION_NOSUCH_COMMAND);
            }
        } catch (DateTimeParseException e) {
            throw new InvalidCommandArgsException(Message.EXCEPTION_INVALID_CMD_ARGS);
        } catch (NumberFormatException e) {
            throw new InvalidCommandArgsException(Message.EXCEPTION_INVALID_TASK_ID_FORMAT);
        }

    }

    /**
     * Handles simple command parsing.
     *
     * @param type {@link DukeCommand} enum command type to create
     * @return an optional command object
     */
    private static Optional<Command> handleSimpleCommand(DukeCommand type) {
        // handle simple commands
        switch (type) {
        case LIST:
            return Optional.of(new ListCommand());
        case BYE:
            return Optional.of(new ExitCommand());
        default:
            return Optional.empty();
        }
    }

    /**
     * Handles single arg command parsing.
     *
     * @param type {@link DukeCommand} enum command type to create
     * @param ops  String[] a list of command arguments
     * @return an optional command object
     */
    private static Optional<Command> handleSingleArgCommand(DukeCommand type, String[] ops)
            throws NumberFormatException, InvalidCommandArgsException {
        // ensure option args matches the format
        if (ops.length != 2) {
            switch (type) {
            case MARK:
                throw new InvalidCommandArgsException(Message.EXCEPTION_INVALID_MARK_CMD);
            case UNMARK:
                throw new InvalidCommandArgsException(Message.EXCEPTION_INVALID_UNMARK_CMD);
            case DELETE:
                throw new InvalidCommandArgsException(Message.EXCEPTION_INVALID_DELETE_CMD);
            case DATE:
                throw new InvalidCommandArgsException(Message.EXCEPTION_INVALID_DATE_CMD);
            case FIND:
                throw new InvalidCommandArgsException(Message.EXCEPTION_INVALID_DATE_CMD);
            default:
                break;
            }
        }

        // handle commands with single args
        switch (type) {
        case MARK:
            assert ops.length == 2 : Message.EXCEPTION_INVALID_MARK_CMD;

            return Optional.of(new MarkCommand(Integer.parseInt(ops[1]), true));
        case UNMARK:
            assert ops.length == 2 : Message.EXCEPTION_INVALID_UNMARK_CMD;

            return Optional.of(new MarkCommand(Integer.parseInt(ops[1]), false));
        case DELETE:
            assert ops.length == 2 : Message.EXCEPTION_INVALID_DELETE_CMD;

            String[] strArray = ops[1].split(" ");
            int[] taskIds = new int[strArray.length];
            for (int i = 0; i < strArray.length; i++) {
                taskIds[i] = Integer.parseInt(strArray[i]);
            }

            return Optional.of(new DeleteCommand(taskIds));
        case DATE:
            assert ops.length == 2 : Message.EXCEPTION_INVALID_DATE_CMD;

            return Optional.of(new ListCommand(DateUtil.toLocalDateTime(ops[1])));
        case FIND:
            assert ops.length == 2 : Message.EXCEPTION_INVALID_DATE_CMD;

            return Optional.of(new ListCommand(ops[1]));
        default:
            return Optional.empty();
        }
    }

    /**
     * Handles multiple arg command parsing.
     *
     * @param type {@link DukeCommand} enum command type to create
     * @param ops  String[] a list of command arguments
     * @return an optional command object
     */
    private static Optional<Command> handleComplexArgCommand(DukeCommand command, String[] ops)
            throws DateTimeParseException, InvalidCommandArgsException {
        // ensure option args matches the format
        if (ops.length != 2) {
            switch (command) {
            case TODO:
                throw new InvalidCommandArgsException(Message.EXCEPTION_INVALID_TODO_CMD);
            case DEADLINE:
                throw new InvalidCommandArgsException(Message.EXCEPTION_INVALID_DATE_CMD);
            case EVENT:
                throw new InvalidCommandArgsException(Message.EXCEPTION_INVALID_EVENT_CMD);
            default:
                break;
            }
        }

        // handle commands with multiple args
        String[] args;
        switch (command) {
        case TODO:
            assert ops.length == 2 : Message.EXCEPTION_INVALID_TODO_CMD;

            return Optional.of(new AddCommand(ops[1], false));
        case DEADLINE:
            assert ops.length == 2 : Message.EXCEPTION_INVALID_DATE_CMD;

            args = ops[1].split(" /[a-z]*[^ ] ");
            if (args.length != 2) {
                throw new InvalidCommandArgsException(Message.EXCEPTION_INVALID_DEADLINE_CMD);
            }

            return Optional.of(new AddCommand(args[0], false, DateUtil.toLocalDateTime(args[1])));
        case EVENT:
            assert ops.length == 2 : Message.EXCEPTION_INVALID_EVENT_CMD;

            args = ops[1].split(" /[a-z]*[^ ] ");
            if (args.length != 3) {
                throw new InvalidCommandArgsException(Message.EXCEPTION_INVALID_EVENT_CMD);
            }

            return Optional.of(new AddCommand(args[0], false, DateUtil.toLocalDateTime(args[1]),
                    DateUtil.toLocalDateTime(args[2])));
        default:
            return Optional.empty();
        }
    }
}
