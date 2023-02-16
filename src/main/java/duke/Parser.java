package duke;

import duke.exceptions.InvalidCommandException;
import duke.exceptions.NoDescriptionException;
import duke.exceptions.TaskNotFoundException;
import duke.exceptions.WrongDateFormatException;


/**
 * This class is a parser that reads user inputs.
 */
public class Parser {

    /**
     * Parses the input string and calls the correct
     * execution method.
     *
     * @param userInput User Input.
     * @param taskList TaskList.
     * @param storage Storage.
     * @param textUi TextUi.
     */
    public String parse(String userInput, TaskList taskList, Storage storage, TextUi textUi) {
        String command;
        String body;
        assert userInput != null : "No user input detected";

        if (userInput.contains(" ")) {
            command = userInput.substring(0, userInput.indexOf(" "));
            body = userInput.substring(userInput.indexOf(" ") + 1);
        } else {
            command = userInput;
            body = "";
        }

        try {
            switch (command) {
            case "list":
                return Commands.executeListCommand(taskList);
            case "mark":
                return Commands.executeMarkCommand(body, taskList, storage);
            case "unmark":
                return Commands.executeUnmarkCommand(body, taskList, storage);
            case "delete":
                return Commands.executeDeleteCommand(body, textUi, taskList, storage);
            case "todo":
                return Commands.executeToDoCommand(body, textUi, taskList, storage);
            case "deadline":
                return Commands.executeDeadlineCommand(body, textUi, taskList, storage);
            case "event":
                return Commands.executeEventCommand(body, textUi, taskList, storage);
            case "find":
                return Commands.executeFindCommand(body, taskList);
            case "tag":
                return Commands.executeTagCommand(body, taskList, storage);
            default:
                throw new InvalidCommandException();
            }
        } catch (TaskNotFoundException | InvalidCommandException
                 | NoDescriptionException | WrongDateFormatException e) {
            return e.getMessage();
        }
    }

    public static String removeWhiteSpaces(String str) {
        return str.replaceAll("\\s", "");
    }
}
