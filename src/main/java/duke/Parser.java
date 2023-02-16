package duke;

import duke.exceptions.*;
import duke.task.*;
import duke.commands.*;

/**
 * Handles the reading and execution of inputs
 */
public class Parser {

    enum Types { LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, BYE, FIND, RESCHEDULE }

    Parser() { }

    /**
     * Reads input from user and splits it into cases based on Types specified
     * in enum
     * @param inputString Input from user
     * @param taskList       List containing all current tasks
     * @param ui             Ui that runs output
     * @return String which contains duke's response to the commands
     * @throws NeroException Throws an exception depending on the exception faced
     */
    String parseCommand(String inputString, TaskList taskList, Ui ui) throws NeroException {
        try {
            String[] input = inputString.split(" ");
            switch (Enum.valueOf(Types.class, input[0].toUpperCase())) {
            case BYE:
                Command newCommand = new ByeCommand(ui, taskList);
                return newCommand.executeCommand(inputString);
            case LIST:
                newCommand = new ListCommand(ui, taskList);
                return newCommand.executeCommand(inputString);
            case MARK:
                newCommand = new MarkCommand(ui, taskList);
                return newCommand.executeCommand(inputString);
            case UNMARK:
                newCommand = new UnmarkCommand(ui, taskList);
                return newCommand.executeCommand(inputString);
            case TODO:
                newCommand = new ToDoCommand(ui, taskList);
                return newCommand.executeCommand(inputString);
            case DEADLINE:
                newCommand = new DeadlineCommand(ui, taskList);
                return newCommand.executeCommand(inputString);
            case EVENT:
                newCommand = new EventCommand(ui, taskList);
                return newCommand.executeCommand(inputString);
            case DELETE:
                newCommand = new DeleteCommand(ui, taskList);
                return newCommand.executeCommand(inputString);
            case FIND:
                newCommand = new FindCommand(ui, taskList);
                return newCommand.executeCommand(inputString);
            case RESCHEDULE:
                newCommand = new RescheduleCommand(ui, taskList);
                return newCommand.executeCommand(inputString);
            default:
                return ui.printCommandNotDetected();
            }
        } catch (IllegalArgumentException e) {
            throw new IncorrectInputException();
        }
    }
}
