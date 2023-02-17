package duke.task;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.EditCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.InvalidCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.exception.DukeException;

/**
 * Class for Parser object.
 *
 * @author Gao Mengqi
 * @version CS2103T AY22/23 Semester 2
 */

public class Parser {
    private static String[] arr;
    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String DELETE = "delete";
    private static final String EVENT = "event";
    private static final String LIST = "list";
    private static final String MARK = "mark";
    private static final String UNMARK = "unmark";
    private static final String BYE = "bye";
    private static final String FIND = "find";
    private static final String EDIT = "edit";
    private static final String HELP = "help";

    /**
     * Splits the command into different parts.
     *
     * @param command
     * @return a String array containing useful information about the command.
     */

    public static String[] splitCommand(String command) {
        String[] temp = command.split(" ", 2);

        if (command.contains("/") && temp[0].equals("deadline")) {
            splitDeadlineCommand(temp);
        } else if (command.contains("/") && temp[0].equals("event")) {
            splitEventCommand(temp);
        } else if (temp[0].equals("edit")) {
            arr = command.split("/");
        } else {
            arr = command.split(" ", 2);
        }
        return arr;
    }

    /**
     * Splits the command for deadline into command, task description and deadline.
     * @param tempArr
     */
    public static void splitDeadlineCommand(String[] tempArr) {
        String[] temp2;
        temp2 = tempArr[1].split("/by ");
        System.out.println(temp2[0]);
        temp2[1] = temp2[1].replace("/", "-");
        arr = new String[tempArr.length + temp2.length - 1];
        arr[0] = tempArr[0];
        System.arraycopy(temp2, 0, arr, tempArr.length - 1, temp2.length);

        if (arr.length != 3) {
            try {
                throw new DukeException("invalid format!");
            } catch (DukeException e) {
                e.getMessage();
            }
        }

    }

    /**
     * Splits the command for event into command, task description from and by.
     * @param tempArr
     */
    public static void splitEventCommand(String[] tempArr) {
        String[] temp2;
        temp2 = tempArr[1].split("/");
        arr = new String[tempArr.length + temp2.length - 1];
        arr[0] = tempArr[0];
        System.arraycopy(temp2, 0, arr, tempArr.length - 1, temp2.length);
    }

    /**
     * Parses the command.
     *
     * @param command
     * @return Command object.
     */

    public static Command parse(String command) throws ArrayIndexOutOfBoundsException {
        Command parsedCommand = null;
        String[] arr = splitCommand(command);
        String commandType = arr[0].trim();

        switch (commandType) {
        case BYE:
            parsedCommand = new ExitCommand();
            break;
        case DELETE:
            int taskNum = Integer.parseInt(arr[1]);
            parsedCommand = new DeleteCommand(taskNum);
            break;
        case TODO:
            parsedCommand = new AddCommand(arr[0], arr[1]);
            break;
        case FIND:
            parsedCommand = new FindCommand(arr[1]);
            break;
        case DEADLINE:
            parsedCommand = new AddCommand(arr[0], arr[1], arr[2]);
            break;
        case EVENT:
            parsedCommand = new AddCommand(arr[0], arr[1], arr[2], arr[3]);
            break;
        case UNMARK:
            int taskNumber = Integer.parseInt(arr[1].trim());
            parsedCommand = new UnmarkCommand(taskNumber);
            break;
        case MARK:
            int getTaskNum = Integer.parseInt(arr[1].trim());
            parsedCommand = new MarkCommand(getTaskNum);
            break;
        case LIST:
            parsedCommand = new ListCommand();
            break;
        case EDIT:
            parsedCommand = new EditCommand(arr[1].trim(), arr[2].trim(), arr[3].trim());
            break;
        case HELP:
            parsedCommand = new HelpCommand();
            break;
        default:
            parsedCommand = new InvalidCommand();
        }
        return parsedCommand;
    }
}
