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

    /**
     * Performs string cleaning on input from user for Deadline tasks
     * @param inputString input from user
     * @return array of strings containing [description of task, deadline of task]
     */
    String[] cleanDeadline(String inputString) {
        String[] cleanedString = new String[2];
        String[] splitString = inputString.split("/");
        String description = splitString[0].replace("deadline", "");
        String deadline = splitString[1].replace("by", "");
        cleanedString[0] = description;
        cleanedString[1] = deadline;
        return cleanedString;
    }

    /**
     * Performs string cleaning on input from user for Event tasks
     * @param inputString input from user
     * @return array of strings containing [description of task, start date of task, end date of task]
     */
    String[] cleanEvent(String inputString) {
        String[] cleanedString = new String[3];
        String[] splitString = inputString.split("/");
        String description = splitString[0].replace("event", "");
        String startDate = splitString[1].replace("from", "");
        String endDate = splitString[2].replace("to", "");
        cleanedString[0] = description;
        cleanedString[1] = startDate;
        cleanedString[2] = endDate;
        return cleanedString;
    }
}
