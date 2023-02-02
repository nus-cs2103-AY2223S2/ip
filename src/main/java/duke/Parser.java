package duke;

import command.ByeCommand;
import command.Command;
import command.DeadlineCommand;
import command.DeleteCommand;
import command.EventCommand;
import command.FindCommand;
import command.ListCommand;
import command.MarkCommand;
import command.TodoCommand;
import command.UnmarkCommand;
import exceptions.BlankException;
import exceptions.DukeException;
import exceptions.InvalidInstructionException;

/**
 * Handles user input.
 *
 * @author Julio Harjo
 */
public class Parser {

    /**
     * Stores tasks in a text file.
     */
    protected static Storage storage;

    /**
     * Handles User interaction.
     */
    protected static Ui ui;

    /**
     * Constructor.
     *
     * @param storage The storage list used.
     * @param ui      The duke.duke.Ui interface used.
     */
    public Parser(Storage storage, Ui ui) {
        this.storage = storage;
        this.ui = ui;
    }

    /***
     * parse the instruction accordingly based on first word in the string
     * @param command
     * @return
     * @throws DukeException
     */
    public static Command parse(String command) throws DukeException {
        String[] c = command.split(" ", 2);
        String instruction = c[0];
        Command com;
        try {
            switch (instruction) {
                case ("list"):
                    com = new ListCommand();
                    break;
                case ("find"):
                    checkIfBlank(c);
                    com = new FindCommand(c[1]);
                    break;
                case ("mark"):
                    checkIfBlank(c);
                    checkIfValidInteger(c);
                    String number = command.substring(5);
                    int taskNum = Integer.parseInt(number);
                    com = new MarkCommand(taskNum);
                    break;
                case ("unmark"):
                    checkIfBlank(c);
                    checkIfValidInteger(c);
                    number = command.substring(7);
                    int index = Integer.parseInt(number);
                    com = new UnmarkCommand(index);
                    break;
                case ("todo"):
                    checkIfBlank(c);
                    com = new TodoCommand(command);
                    break;
                case ("deadline"):
                    checkIfBlank(c);
                    String[] limit = c[1].split(" /by ");
                    checkIfBlank(limit);
                    com = new DeadlineCommand(command);
                    break;
                case ("event"):
                    checkIfBlank(c);
                    String[] interval = c[1].split(" /from ");
                    checkIfBlank(interval);
                    com = new EventCommand(command);
                    break;
                case ("delete"):
                    checkIfBlank(c);
                    checkIfValidInteger(c);
                    int taskNum2 = Integer.parseInt(c[1]);
                    com = new DeleteCommand(taskNum2);
                    break;
                case ("bye"):
                    com = new ByeCommand();
                    break;
                default:
                    throw new InvalidInstructionException();
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException("some msg");
        }
        return com;
    }

    /**
     * checks if input after command is blank
     *
     * @param arr
     * @throws BlankException
     */
    public static void checkIfBlank(String[] arr) throws BlankException {
        try {
            if (arr[1].trim().isBlank())
                throw new BlankException();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new BlankException();
        }
    }

    /**
     * checks if input for commands with integers is valid
     *
     * @param arr
     * @throws NumberFormatException
     */
    public static void checkIfValidInteger(String[] arr) throws NumberFormatException {
        try {
            Integer.parseInt(arr[1]);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Please provide a valid number");
        }
    }
}
