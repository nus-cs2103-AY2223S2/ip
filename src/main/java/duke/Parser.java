package duke;

import duke.Command.*;
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

    public String runCommand(String inputCommand, TaskList tasks, Storage storage, Ui ui) throws DukeException{
        int selectedNum;
        String commandResponse;
        commandResponse = "";

        String[] inputCmdArr = inputCommand.split(" ");
        switch(inputCmdArr[0]) {
        case "list":
            commandResponse+=tasks.returnTaskAsString();
            break;
        case "todo":
            String todoDesc;
            todoDesc = inputCommand.split(" ", 2)[1];
            tasks.addTask(todoDesc, false);
            commandResponse+=tasks.returnNewestTaskAsString();
            storage.save(tasks);
            break;
        case "deadline":
            String deadlineInput;
            deadlineInput = inputCommand.split(" ", 2)[1];
            String[] deadlineDesc = deadlineInput.split(" /by ");
            tasks.addTask(deadlineDesc[0], deadlineDesc[1], false);
            commandResponse+=tasks.returnNewestTaskAsString();
            storage.save(tasks);
            break;
        case "event":
            String eventInput;
            eventInput = inputCommand.split(" ", 2)[1];
            String[] eventDescArr = eventInput.split(" /from ");
            String eventDesc = eventDescArr[0];
            String[] eventTimeArr = eventDescArr[1].split(" /to ");
            String eventFrom = eventTimeArr[0];
            String eventTo = eventTimeArr[1];
            tasks.addTask(eventDesc, eventFrom, eventTo, false);
            commandResponse+=tasks.returnNewestTaskAsString();
            storage.save(tasks);
            break;
        case "mark":
            selectedNum = Integer.parseInt(inputCmdArr[1]);
            commandResponse+=tasks.markTaskWithResult(selectedNum);
            storage.save(tasks);
            break;
        case "unmark":
            selectedNum = Integer.parseInt(inputCmdArr[1]);
            commandResponse+=tasks.unMarkTaskWithResult(selectedNum);
            storage.save(tasks);
            break;
        case "delete":
            int numToDelete;
            numToDelete = Integer.parseInt(inputCommand.split(" ", 2)[1]);
            commandResponse+=tasks.deleteTaskWithResult(numToDelete);
            storage.save(tasks);
            break;
        case "search":
            String searchStr;
            searchStr = inputCommand.split(" ", 2)[1];
            commandResponse+=tasks.searchTaskWithResult(searchStr);
            break;
        default:
            commandResponse+="This command is not supported!";
        }
        return commandResponse;
    }

    public String runCommand2(String inputCommand, TaskList tasks, Storage storage, Ui ui) throws DukeException {
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
