package duke;

import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddTodoCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

/**
 * Parsers input from user.
 */
public class Parser {
    static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd MMM uuuu kk:mm");

    /**
     * Returns a Command object corresponding to the command input by user.
     *
     * @param input A String command by the user.
     * @return Command A corresponding Command object.
     * @throws DukeException
     */
    public static Command parse(String input) throws DukeException {
        String[] inputs = input.split(" /");
        String instruction = getInstruction(inputs);
        checkValidCommand(instruction, inputs);
        Command command = getCommand(instruction, inputs);
        return command;
    }

    /**
     * Gets instruction from user input.
     *
     * @param inputs The user input.
     * @return String The instruction word.
     */
    private static String getInstruction(String[] inputs) {
        String instruction = inputs[0].split(" ", 2)[0];
        return instruction.toLowerCase();
    }

    /**
     * Splits the instruction from task name or selected number.
     *
     * @param inputs The user input.
     * @return String[] The split input.
     */
    private static String[] splitFromInstruction(String inputs) {
        return inputs.split(" ", 2);
    }

    /**
     * Checks if a user input is valid. Throws a DukeException is it is invalid.
     *
     * @param instruction The instruction from user input.
     * @param inputs The user input.
     * @throws DukeException
     */
    private static void checkValidCommand(String instruction, String[] inputs) throws DukeException {
        switch(instruction) {

        case "bye":
        case "list":
            if(inputs.length != 1) {
                throw new DukeException(Ui.unreadableCommandResponse);
            }
            break;

        case "find":
            if(inputs.length != 1 || splitFromInstruction(inputs[0]).length != 2) {
                throw new DukeException(Ui.incompleteFindCommandResponse);
            }
            break;

        case "mark":
        case "unmark":
        case "delete":
            if(inputs.length != 1 || splitFromInstruction(inputs[0]).length != 2) {
                throw new DukeException(Ui.incompleteSelectionCommandResponse);
            }

            String num = splitFromInstruction(inputs[0])[1];
            if(!isValidSelection(num)) {
                throw new DukeException(Ui.invalidSelectionCommandResponse);
            }
            break;

        case "todo":
            if(inputs.length != 1 || splitFromInstruction(inputs[0]).length != 2) {
                throw new DukeException(Ui.incompleteAddTodoCommandResponse);
            }
            break;

        case "deadline":
            if(inputs.length != 2 || splitFromInstruction(inputs[0]).length != 2) {
                throw new DukeException(Ui.incompleteAddDeadlineCommandResponse);
            }

            String by = inputs[1].substring(3);
            boolean hasValidBy = isValidTime(by);
            if(!hasValidBy) {
                throw new DukeException(Ui.invalidTimeResponse);
            }
            break;

        case "event":
            if(inputs.length != 3 || splitFromInstruction(inputs[0]).length != 2) {
                throw new DukeException(Ui.incompleteAddEventCommandResponse);
            }

            String from = inputs[1].substring(5);
            String to = inputs[2].substring(3);
            boolean hasValidDuration = isValidTime(from, to);
            if(!hasValidDuration) {
                throw new DukeException(Ui.invalidTimeResponse);
            }
            break;

        default:
            throw new DukeException(Ui.unreadableCommandResponse);
        }
    }

    /**
     * Checks if the time is in correct format.
     *
     * @param inputs The time in the form of a String inputted by the user.
     * @return boolean Whether the time is in correct format.
     */
    private static boolean isValidTime(String... inputs) {
        try {
            for(String x: inputs) {
                LocalDateTime.parse(x, FORMAT);
            }
        } catch(DateTimeParseException e) {
            return false;
        }
        return true;
    }

    /**
     * Checks if the input by user to select task is valid.
     *
     * @param num The String input by user.
     * @return boolean Whether the select task input by user is valid.
     */
    private static boolean isValidSelection(String num) {
        try {
            Integer.parseInt(num);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * Returns the Command based on user input.
     *
     * @param instruction The instruction from user input.
     * @param inputs The user input.
     * @return Command The Command created by inputs from the user.
     */
    private static Command getCommand(String instruction, String[] inputs) {
        Command command = null;
        switch(instruction) {

        case "bye":
            command = new ExitCommand();
            break;

        case "list":
            command = new ListCommand();
            break;

        case "find":
            String keyword = splitFromInstruction(inputs[0])[1];
            command = new FindCommand(keyword);
            break;

        case "mark":
            int markNum = Integer.parseInt(splitFromInstruction(inputs[0])[1]);
            command = new MarkCommand(markNum);
            break;

        case "unmark":
            int unmarkNum = Integer.parseInt(splitFromInstruction(inputs[0])[1]);
            command = new UnmarkCommand(unmarkNum);
            break;

        case "delete":
            int deleteNum = Integer.parseInt(splitFromInstruction(inputs[0])[1]);
            command = new DeleteCommand(deleteNum);
            break;

        case "todo":
            String todoName = splitFromInstruction(inputs[0])[1];
            command = new AddTodoCommand(todoName);
            break;

        case "deadline":
            String deadlineName = splitFromInstruction(inputs[0])[1];
            String by = inputs[1].substring(3);
            command = new AddDeadlineCommand(deadlineName, by);
            break;

        case "event":
            String eventName = splitFromInstruction(inputs[0])[1];
            String from = inputs[1].substring(5);
            String to = inputs[2].substring(3);
            command = new AddEventCommand(eventName, from, to);
            break;

        default:
            command = null;
        }

        return command;
    }
}
