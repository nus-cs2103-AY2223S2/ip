package parser;

import command.*;
import exceptions.TaskException;
import java.util.Arrays;

public class Parser {

    // Enum list to contain instructions to be entered by user to control system
    public enum Instructions {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE
    }

    public Command parse(String input) throws TaskException {
        String[] part = input.split(" ");
        int index = 0;
        String first_word = part[0].toUpperCase();

        if (part[0].equals("mark") || part[0].equals("unmark") || part[0].equals("delete")) {
            index = Integer.parseInt(part[1]) - 1;
        }

        if (!Arrays.stream(Instructions.values()).anyMatch(x -> (x.toString()).equals(first_word))) {
            return new DefaultCommand();
        }

        Instructions instruction = Instructions.valueOf(first_word);

        switch (instruction) {
            // Exit the system upon entering
            case BYE:
                return new ByeCommand();

            // Display a list of tasks that shows its completion and types
            case LIST:
                return new ListCommand();

            // Mark to complete the task, the second bracket will show a cross
            case MARK:
                return new MarkCommand(index);

            // Un-mark to redo the completion of the task, the cross will be
            // removed from the second bracket
            case UNMARK:
                return new UnmarkCommand(index);

            // Add task of type (To do/ deadline/ event)
            case TODO:
            case DEADLINE:
            case EVENT: {
                return new AddCommand(instruction.toString(), input);
            }

            // Delete task from the list according to its numbering on the list
            case DELETE:
                return new DeleteCommand(index);

            // default will throw an exception in case switch-case is unable to find
            // instruction
            default:
                return new DefaultCommand();
        }
    }
}