package duke.task;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;

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

    /**
     * Split the command into different parts.
     *
     * @param command
     * @return a String array containing useful information about the command.
     */

    public static String[] splitCommand(String command) {
        if (command.contains("/")) {
            String[] temp2;
            String[] temp = command.split(" ", 2);
            if (temp[0].equals("deadline")) {
                temp2 = temp[1].split("/", 2);
            } else {
                temp2 = temp[1].split("/");
            }
            temp2[1] = temp2[1].replace("/", "-");
            arr = new String[temp.length + temp2.length - 1];
            arr[0] = temp[0];
            System.arraycopy(temp2, 0, arr, temp.length - 1, temp2.length);
        } else {
            arr = command.split(" ", 2);
        }
        return arr;
    }

    /**
     * Parse the command.
     *
     * @param command
     * @return Command object.
     */

    public static Command parse(String command) {
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
            try {
                parsedCommand = new AddCommand(arr[0], arr[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("task cannot be empty!");
            } finally {
                break;
            }
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
        default:
            System.out.println("No such command!");
        }
        return parsedCommand;
    }
}
