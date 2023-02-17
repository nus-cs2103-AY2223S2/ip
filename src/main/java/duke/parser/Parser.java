package duke.parser;
import java.util.ArrayList;

import duke.exception.DukeException;
import duke.exception.EmptyDescriptionException;
import duke.exception.WrongCommandException;
import duke.exception.WrongFormatException;
import duke.task.TaskList;
import duke.ui.Ui;


/**
 * A parser to understand and perform user command
 */
public class Parser {
    private TaskList taskList;
    private ArrayList<String> listOfCommands;

    /**
     * Constructor for Parser
     * @param taskList
     */
    public Parser(TaskList taskList) {
        this.taskList = taskList;
        this.listOfCommands = new ArrayList<>() {
            {
                add("mark");
                add("unmark");
                add("todo");
                add("deadline");
                add("event");
                add("delete");
                add("find");
            }
        };
    }

    /**
     * Perform the user command and create the tasks
     * @throws EmptyDescriptionException If no description of task after the command word
     * @throws WrongCommandException If wrong command word is being entered
     */
    public String performCommand(String input, Ui ui) {
        String trimmedInput = input.trim();
        String[] arrOfString = trimmedInput.split(" ", 2);
        String command = arrOfString[0];
        try {
            this.checkCommand(input, command);
            switch (command) {
            case "bye":
                return HandleBye.performBye(trimmedInput, ui);
            case "list":
                return HandleList.performList(trimmedInput, taskList, ui);
            case "mark":
                return HandleMark.performMark(trimmedInput, taskList, ui);
            case "unmark":
                return HandleUnmark.performUnmark(trimmedInput, taskList, ui);
            case "todo":
                return HandleToDo.performToDo(trimmedInput, taskList, ui);
            case "deadline":
                return HandleDeadline.performDeadline(trimmedInput, taskList, ui);
            case "event":
                return HandleEvent.performEvent(trimmedInput, taskList, ui);
            case "delete":
                return HandleDelete.performDelete(trimmedInput, taskList, ui);
            case "find":
                return HandleFind.performFind(trimmedInput, taskList, ui);
            default:
                assert false : "Unable to process command";
                return ui.showError("Please enter a valid command and/or task!");
            }
        } catch (EmptyDescriptionException | WrongCommandException
                | WrongFormatException | DukeException e) {
            return ui.showError(e.getMessage());
        }
    }

    /**
     * Check whether the command and input is valid or invalid
     * @param input Input entered by user
     * @param command
     * @throws EmptyDescriptionException Exception thrown when there is no description/ task number after the command
     * @throws WrongCommandException Exception thrown when the command does not exist
     */
    public void checkCommand(String input, String command) throws EmptyDescriptionException, WrongCommandException {
        if (listOfCommands.contains(input)) {
            throw new EmptyDescriptionException();
        } else if (!listOfCommands.contains(command) && !command.equals("list") && !command.equals("bye")) {
            throw new WrongCommandException();
        }
    }

}




