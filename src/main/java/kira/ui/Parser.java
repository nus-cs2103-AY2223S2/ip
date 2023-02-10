package kira.ui;

import kira.command.ByeCommand;
import kira.command.Command;
import kira.command.DeadlineCommand;
import kira.command.DeleteCommand;
import kira.command.EventCommand;
import kira.command.FindCommand;
import kira.command.ListCommand;
import kira.command.MarkCommand;
import kira.command.ToDoCommand;
import kira.command.TodayCommand;
import kira.command.UnmarkCommand;
import kira.exception.KiraException;
import kira.task.Deadline;
import kira.task.Event;
import kira.task.TaskType;
import kira.task.ToDo;

/**
 * CommandString are all possible commands.
 */
enum CommandString {
    BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, TODAY, FIND
}

/**
 * Parser handles the parsing of user inputs into executable commands.
 */
public class Parser {

    /**
     * Parses the input to an executable command.
     *
     * @param input input string to be parsed
     * @return executable command
     * @throws KiraException Incorrect-Format, Invalid-Index
     */
    public static Command parse(String input) throws KiraException {
        String[] temp = input.split(" ", 2);
        try {
            CommandString commandString = CommandString.valueOf(temp[0].toUpperCase());
            switch (commandString) {
            case BYE:
                if (temp.length != 1) {
                    throw new KiraException("Incorrect use of BYE.\n"
                            + "There should be no extra arguments");
                }
                return new ByeCommand();
            case LIST:
                if (temp.length != 1) {
                    throw new KiraException("Incorrect use of LIST.\n"
                            + "There should be no extra arguments");
                }
                return new ListCommand();
            case TODAY:
                if (temp.length != 1) {
                    throw new KiraException("Incorrect use of TODAY.\n"
                            + "There should be no extra arguments");
                }
                return new TodayCommand();
            case MARK:
                if (temp.length != 2) {
                    throw new KiraException("Incorrect use of MARK"
                            + "Format: mark <index>");
                }
                return new MarkCommand(Integer.valueOf(temp[1]));
            case UNMARK:
                if (temp.length != 2) {
                    throw new KiraException("Incorrect use of UNMARK"
                            + "Format: unmark <index>");
                }
                return new UnmarkCommand(Integer.valueOf(temp[1]));
            case DELETE:
                if (temp.length != 2) {
                    throw new KiraException("Incorrect use of DELETE"
                            + "Format: delete <index>");
                }
                return new DeleteCommand(Integer.valueOf(temp[1]));
            case FIND:
                if (temp.length != 2) {
                    throw new KiraException("Incorrect use of FIND"
                            + "Format: find <description>");
                }
                return new FindCommand(temp[1]);
            case TODO:
                // Fallthrough
            case DEADLINE:
                // Fallthrough
            case EVENT:
                if (temp.length != 2) {
                    throw new KiraException("Incorrect use of command for any TASK commands");
                }
                return parseTask(commandString.toString(), temp[1]);
            default:
                assert false : commandString;
                // Should never reach here. Programmer error!
                throw new KiraException("Unexpected Error!");
            }
        } catch (NumberFormatException e) {
            throw new KiraException("Index is not a number...\nPlease check your arguments.");
        } catch (IllegalArgumentException e) {
            throw new KiraException("Sorry, I don't know this command :C");
        }
    }

    /**
     * Parses the task with additional checks on validity.
     *
     * @param commandString
     * @param taskArguments
     * @return executable command
     * @throws KiraException Incorrect-Format
     */
    private static Command parseTask(String commandString, String taskArguments) throws KiraException {
        TaskType type = TaskType.valueOf(commandString);
        switch (type) {
        case TODO:
            return new ToDoCommand(new ToDo(taskArguments));
        case DEADLINE:
            String[] format = taskArguments.split(" /by ", 2);
            if (format.length != 2) {
                throw new KiraException("Incorrect deadline format :C\n"
                        + "Please follow this format for deadline:\n"
                        + "deadline <description> /by yyyy-MM-dd HHmm");
            }
            return new DeadlineCommand(new Deadline(format[0], format[1]));
        case EVENT:
            String errMsg = "Incorrect event format :C\n"
                    + "Please follow this format for event:\n"
                    + "event <description> /from yyyy-MM-dd HHmm "
                    + "/to yyyy-MM-dd HHmm";
            format = taskArguments.split(" /from ", 2);
            if (format.length != 2) {
                throw new KiraException(errMsg);
            }
            String desc = format[0];
            format = format[1].split(" /to ", 2);
            if (format.length != 2) {
                throw new KiraException(errMsg);
            }
            return new EventCommand(new Event(desc, format[0], format[1]));
        default:
            assert false : type;
            // Should never reach here. Programmer error!
            throw new KiraException("Unexpected Error!");
        }
    }

}
