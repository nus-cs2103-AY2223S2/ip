package util;

import command.ByeCommand;
import command.Command;
import command.DeadlineCommand;
import command.DeleteCommand;
import command.EventCommand;
import command.FindCommand;
import command.HelpCommand;
import command.ListCommand;
import command.MarkCommand;
import command.TodoCommand;
import command.UnmarkCommand;

/**
 * A library of useful methods to parse various String
 * user inputs and retrieve data from it.
 */
public class Parser {
    private String[] inputArr;
    private FileManager fileManager;

    public Parser(FileManager fileManager) {
        this.fileManager = fileManager;
    }
    /**
     * Removes leading and trailing whitespaces in user
     * input and extracts the command.
     *
     * @param input
     * @return command String
     */
    public Command parse(String input) throws DukeException {
        String ip = input;

        this.inputArr = ip.split(" ", 2);
        String userCommand = inputArr[0];

        try {
            switch (userCommand) {
            case "help":
                return new HelpCommand();
            case "list":
                return new ListCommand();
            case "bye":
                assert fileManager != null;
                return new ByeCommand(fileManager);
            case "find":
                return new FindCommand(inputArr[1]);
            case "check":
                return new MarkCommand(inputArr[1]);
            case "uncheck":
                return new UnmarkCommand(inputArr[1]);
            case "delete":
                return new DeleteCommand(inputArr[1]);
            case "todo":
                return new TodoCommand(inputArr[1]);
            case "event":
                return new EventCommand(inputArr[1]);
            case "deadline":
                return new DeadlineCommand(inputArr[1]);
            case "":
                throw new DukeException(DukeUI.emptyInputErrorMessage());
            default:
                throw new DukeException(DukeUI.invalidInputErrorMessage());
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException(DukeUI.incompleteInputErrorMessage());
        } catch (DukeException e) {
            String str = e.getMessage();
            throw new DukeException(str);
        }
    }

    //credit: https://stackabuse.com/java-check-if-string-is-a-number/
    /**
     * Checks if a given String is numeric.
     *
     * @param string
     * @return true if String is numeric and false otherwise
     */
    public static boolean isNumeric(String string) {
        int intValue;

        if (string == null || string.equals("")) {
            System.out.println("String cannot be parsed, it is null or empty.");
            return false;
        }

        try {
            intValue = Integer.parseInt(string);
            assert (Integer) intValue != null;
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Illegal string input!");
        }
        return false;
    }
}
