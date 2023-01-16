package duke.parser;

import java.time.format.DateTimeParseException;

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
     * Parses csv string "T,1,..." into an AddCommand object
     * 
     * @param input {@link String} object
     * @return {@link AddCommand} object
     * @throws DukeException
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
                return new AddCommand(DukeCommand.TODO, title, isDone);
            case "D":
                if (ops.length != 4) {
                    throw new InvalidCommandArgsException(Message.EXCEPTION_INVALID_DATE_CMD);
                }
                return new AddCommand(DukeCommand.DEADLINE, title, isDone, DateUtil.toLocalDateTime(ops[3]));
            case "E":
                if (ops.length != 5) {
                    throw new InvalidCommandArgsException(Message.EXCEPTION_INVALID_EVENT_CMD);
                }
                return new AddCommand(DukeCommand.EVENT, title, isDone, DateUtil.toLocalDateTime(ops[3]),
                        DateUtil.toLocalDateTime(ops[4]));
            default:
                throw new NoSuchCommandException(Message.EXCEPTION_NOSUCH_COMMAND);
        }

    }

    /**
     * Parses the raw command and trigger the respective duke actions.
     * 
     * @param fullCommand {@link String} object
     * @return {@link Command} object
     * @throws DukeException
     */
    public static Command parse(String fullCommand) throws DukeException {
        String[] ops = fullCommand.split(" ", 2);
        DukeCommand command = null;

        try {
            command = DukeCommand.valueOf(ops[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new NoSuchCommandException(Message.EXCEPTION_NOSUCH_COMMAND);
        }

        // handle simple commands
        switch (command) {
            case LIST:
                return new ListCommand();
            case BYE:
                return new ExitCommand();
            default:
                break;
        }
        try {
            // handle commands with single args
            switch (command) {
                case MARK:
                    if (ops.length != 2) {
                        throw new InvalidCommandArgsException(Message.EXCEPTION_INVALID_MARK_CMD);
                    }
                    return new MarkCommand(Integer.parseInt(ops[1]), true);
                case UNMARK:
                    if (ops.length != 2) {
                        throw new InvalidCommandArgsException(Message.EXCEPTION_INVALID_UNMARK_CMD);
                    }
                    return new MarkCommand(Integer.parseInt(ops[1]), false);
                case DELETE:
                    if (ops.length != 2) {
                        throw new InvalidCommandArgsException(Message.EXCEPTION_INVALID_DELETE_CMD);
                    }
                    return new DeleteCommand(Integer.parseInt(ops[1]));
                case DATE:
                    if (ops.length != 2) {
                        throw new InvalidCommandArgsException(Message.EXCEPTION_INVALID_DATE_CMD);
                    }
                    return new ListCommand(ops[1]);
                default:
                    break;
            }

            String[] args;
            switch (command) {
                case TODO:
                    if (ops.length != 2) {
                        throw new InvalidCommandArgsException(Message.EXCEPTION_INVALID_TODO_CMD);
                    }
                    return new AddCommand(DukeCommand.TODO, ops[1], false);
                case DEADLINE:
                    if (ops.length != 2) {
                        throw new InvalidCommandArgsException(Message.EXCEPTION_INVALID_DATE_CMD);
                    }
                    args = ops[1].split(" /[a-z]*[^ ] ");
                    if (args.length != 2) {
                        throw new InvalidCommandArgsException(Message.EXCEPTION_INVALID_DEADLINE_CMD);
                    }
                    return new AddCommand(DukeCommand.DEADLINE, args[0], false, DateUtil.toLocalDateTime(args[1]));
                case EVENT:
                    if (ops.length != 2) {
                        throw new InvalidCommandArgsException(Message.EXCEPTION_INVALID_EVENT_CMD);
                    }
                    args = ops[1].split(" /[a-z]*[^ ] ");
                    if (args.length != 3) {
                        throw new InvalidCommandArgsException(Message.EXCEPTION_INVALID_EVENT_CMD);
                    }
                    return new AddCommand(DukeCommand.EVENT, args[0], false, DateUtil.toLocalDateTime(args[1]),
                            DateUtil.toLocalDateTime(args[2]));

                default:
                    throw new NoSuchCommandException(Message.EXCEPTION_NOSUCH_COMMAND);
            }
        } catch (DateTimeParseException e) {
            throw new InvalidCommandArgsException(Message.EXCEPTION_INVALID_CMD_ARGS);
        } catch (NumberFormatException e) {
            throw new InvalidCommandArgsException(Message.EXCEPTION_INVALID_TASK_ID_FORMAT);
        }

    }

}
