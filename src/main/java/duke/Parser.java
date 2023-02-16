package duke;

import duke.Command.Command;
import duke.Command.DeadlineCommand;
import duke.Command.DeleteCommand;
import duke.Command.EventCommand;
import duke.Command.ListCommand;
import duke.Command.MarkCommand;
import duke.Command.SearchCommand;
import duke.Command.ToDoCommand;
import duke.Command.UnknownCommand;
import duke.Command.UnmarkCommand;
import duke.ui.Ui;

/**
 * This is the class that parses the command sent to Duke.
 */
public class Parser {

    /**
     * Constructor for the Parser class.
     */
    public Parser() {
    }

    /**
     * This method checks if the command entered is to exit.
     *
     * @param s The command to check.
     * @return True if the command is to exit.
     */
    public boolean isExit(String s) {
        return s.equalsIgnoreCase("bye");
    }

    /**
     * This method runs the main command that the user inputs to Duke.
     *
     * @param inputCommand The command for Duke to process.
     * @param tasks The TaskList for Duke to process.
     * @param storage The storage for Duke's files.
     * @return The String representation of Duke's response.
     * @throws DukeException
     */
    public String runCommand(String inputCommand, TaskList tasks, Storage storage) throws DukeException {
        int selectedNum;
        String commandParams;
        String commandResponse;
        commandParams = "";
        commandResponse = "";

        Command pendingCommand;

        String[] inputCmdArr = inputCommand.split(" ");
        switch(inputCmdArr[0]) {
            case "list":
                pendingCommand = new ListCommand();
                break;
            case "todo":
                commandParams = getParams(inputCommand);
                pendingCommand = new ToDoCommand(commandParams);
                break;
            case "deadline":
                commandParams = getParams(inputCommand);
                pendingCommand = new DeadlineCommand(commandParams);
                break;
            case "event":
                commandParams = getParams(inputCommand);
                pendingCommand = new EventCommand(commandParams);
                break;
            case "mark":
                commandParams = getParams(inputCommand);
                pendingCommand = new MarkCommand(commandParams);
                break;
            case "unmark":
                commandParams = getParams(inputCommand);
                pendingCommand = new UnmarkCommand(commandParams);
                break;
            case "delete":
                commandParams = getParams(inputCommand);
                pendingCommand = new DeleteCommand(commandParams);
                break;
            case "search":
                commandParams = getParams(inputCommand);
                pendingCommand = new SearchCommand(commandParams);
                break;
            default:
                pendingCommand = new UnknownCommand();
        }
        return pendingCommand.executeCommand(storage, tasks);
    }

    private String getParams(String input) throws DukeException {
        String[] inputArr;
        String returnValue;

        try {
            inputArr = input.split(" ", 2);
            returnValue = inputArr[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("There is no parameter specified!");
        }
        return returnValue;
    }
}
