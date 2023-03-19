package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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
import duke.command.UpdateCommand;

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
        String editInput = removeWhitespaceEnter(input);
        String[] inputs = editInput.split(" /");
        String instruction = getInstruction(inputs);
        checkValidCommand(instruction, inputs);
        Command command = getCommand(instruction, inputs);
        return command;
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
            if (inputs.length != 1) {
                throw new DukeException(Ui.unreadableCommandResponse);
            }
            break;

        case "find":
            if (inputs.length != 1 || splitFromInstruction(inputs[0]).length != 2) {
                throw new DukeException(Ui.incompleteFindCommandResponse);
            }
            break;

        case "mark":
        case "unmark":
        case "delete":
            if (inputs.length != 1 || splitFromInstruction(inputs[0]).length != 2) {
                throw new DukeException(Ui.incompleteSelectionCommandResponse);
            }

            String selectNum = splitFromInstruction(inputs[0])[1];
            if (!isValidSelection(selectNum)) {
                throw new DukeException(Ui.invalidSelectionCommandResponse);
            }
            break;

        case "update":
            if (inputs.length < 2) {
                throw new DukeException(Ui.incompleteSelectionCommandResponse);
            } else if (splitFromInstruction(inputs[0]).length != 2) {
                throw new DukeException(Ui.incompleteUpdateCommandResponse);
            }

            String updateNum = splitFromInstruction(inputs[0])[1];
            String[] items = inputs[1].split(":", 2);
            if (items.length != 2) {
                throw new DukeException(Ui.incompleteUpdateCommandResponse);
            }
            if (!isValidSelection(updateNum)) {
                throw new DukeException(Ui.invalidSelectionCommandResponse);
            } else if (!isValidItem(items[0], items[1])) {
                throw new DukeException(Ui.incompleteUpdateCommandResponse);
            }
            break;

        case "todo":
            if (inputs.length != 1 || splitFromInstruction(inputs[0]).length != 2) {
                throw new DukeException(Ui.incompleteAddTodoCommandResponse);
            }
            break;

        case "deadline":
            if (inputs.length != 2 || splitFromInstruction(inputs[0]).length != 2) {
                throw new DukeException(Ui.incompleteAddDeadlineCommandResponse);
            }

            String by = inputs[1].substring(3);
            boolean hasValidBy = isValidTime(by);
            if (!hasValidBy) {
                throw new DukeException(Ui.invalidTimeResponse);
            }
            break;

        case "event":
            if (inputs.length != 3 || splitFromInstruction(inputs[0]).length != 2) {
                throw new DukeException(Ui.incompleteAddEventCommandResponse);
            }

            String from = inputs[1].substring(5);
            String to = inputs[2].substring(3);
            boolean hasValidDuration = isValidTime(from, to);
            if (!hasValidDuration) {
                throw new DukeException(Ui.invalidTimeResponse);
            }
            break;

        default:
            throw new DukeException(Ui.unreadableCommandResponse);
        }
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
            assert(inputs.length == 1);

            command = new ExitCommand();
            break;

        case "list":
            assert(inputs.length == 1);

            command = new ListCommand();
            break;

        case "find":
            assert(inputs.length == 1);

            String keyword = splitFromInstruction(inputs[0])[1];
            command = new FindCommand(keyword);
            break;

        case "mark":
            assert(inputs.length == 1);
            assert(isValidSelection(splitFromInstruction(inputs[0])[1]));

            int markNum = Integer.parseInt(splitFromInstruction(inputs[0])[1]);
            command = new MarkCommand(markNum);
            break;

        case "unmark":
            assert(inputs.length == 1);
            assert(isValidSelection(splitFromInstruction(inputs[0])[1]));

            int unmarkNum = Integer.parseInt(splitFromInstruction(inputs[0])[1]);
            command = new UnmarkCommand(unmarkNum);
            break;

        case "delete":
            assert(inputs.length == 1);
            assert(isValidSelection(splitFromInstruction(inputs[0])[1]));

            int deleteNum = Integer.parseInt(splitFromInstruction(inputs[0])[1]);
            command = new DeleteCommand(deleteNum);
            break;

        case "update":
            int updateNum = Integer.parseInt(splitFromInstruction(inputs[0])[1]);
            String item = getItemCategory(inputs[1])[0].toLowerCase();
            String newInfo = getItemCategory(inputs[1])[1];
            command = new UpdateCommand(updateNum, item, newInfo);
            break;

        case "todo":
            assert(inputs.length == 1);

            String todoName = splitFromInstruction(inputs[0])[1];
            command = new AddTodoCommand(todoName);
            break;

        case "deadline":
            assert(inputs.length == 2);

            String deadlineName = splitFromInstruction(inputs[0])[1];
            String by = inputs[1].substring(3);

            assert(isValidTime(by));

            command = new AddDeadlineCommand(deadlineName, by);
            break;

        case "event":
            assert(inputs.length == 3);

            String eventName = splitFromInstruction(inputs[0])[1];
            String from = inputs[1].substring(5);
            String to = inputs[2].substring(3);

            assert(isValidTime(from, to));

            command = new AddEventCommand(eventName, from, to);
            break;

        default:
            command = null;
        }

        return command;
    }

    /**
     * Removes line breaks and unnecessary whitespaces from input.
     *
     * @param input The String input from user.
     * @return String The String input without line break and unnecessary whitespaces.
     */
    private static String removeWhitespaceEnter(String input) {
        return input.trim().replaceAll("\n", "");
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

    private static String[] getItemCategory(String input) {
        String[] items = input.split(":", 2);
        return items;
    }


    private static boolean isValidItem(String item, String newInformation) {
        if (item.equals("by") || item.equals("from") || item.equals("to")) {
            return isValidTime(newInformation);
        }
        return true;
    }

    /**
     * Checks if the time is in correct format.
     *
     * @param inputs The time in the form of a String inputted by the user.
     * @return boolean Whether the time is in correct format.
     */
    private static boolean isValidTime(String... inputs) {
        try {
            for (String x: inputs) {
                LocalDateTime.parse(x, FORMAT);
            }
        } catch (DateTimeParseException e) {
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
}
