package duke.parser;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.command.Command;
import duke.command.ExitCommand;
import duke.command.FindFreeTimeCommand;
import duke.command.ListCommand;
import duke.command.MassDeleteCommand;
import duke.command.ReminderCommand;
import duke.command.SortCommand;
import duke.exception.DukeException;
import duke.exception.InvalidInputException;

/**
* A parser that parse the input String into a Duke Instruction with respective information encapsulated.
*/
public class Parser {

    public enum Instruction {
        BYE,
        LIST,
        REMINDER,
        HELP,
        MASSDELETE,
        MARK,
        UNMARK,
        DELETE,
        TODO,
        DEADLINE,
        EVENT,
        FIND,
        VIEW,
        UPDATE,
        FREE,
        FIXED,
        SORT;
    }

    /**
     * A parse method that takes in a String representation of a Command, using
     * regular expression to parse it can construct to a Command object.
     *
     * @param input The given String of Command to be parsed by the parser
     * @return The actual Command objected represented by the given input String
     * @throws DukeException Throws exception when invalid input is given
     */
    public static Command parse(String input) throws DukeException {
        Matcher instructionExtractor = extractInstructionAndInformation(input);
        String instructionTag = instructionExtractor.group("instructionTag").strip();
        String information = instructionExtractor.group("information").strip();

        Instruction instruction = matchInstructionTag(instructionTag);
        return createCommand(instruction, information);
    }

    /**
     * Extracts the instruction tag and information from the input by using a regular expression.
     *
     * @param input The input to be parsed.
     * @return A Matcher object that contains the instruction tag and information extracted from the input.
     * @throws InvalidInputException If the input does not match the regular expression.
     */
    private static Matcher extractInstructionAndInformation(String input) throws InvalidInputException {
        //@@author Yufannnn-reused
        //Reused from https://github.com/wweqg/ip/blob/master/src/main/java/duke/parser/Parser.java
        //with minor modification, it is a pretty clean and concise regular expression for general instructions
        Matcher instructionExtractor = Pattern
                .compile("(?<instructionTag>\\S++)(?<information>.*)").matcher(input.trim());
        //@@author

        // Check if the input matches the regular expression
        if (!instructionExtractor.matches()) {
            // If not, throw an exception
            throw new InvalidInputException(ErrorMessage.EMPTY_ERROR);
        }
        // Return the Matcher object that contains the instruction tag and information
        return instructionExtractor;
    }

    /**
     * Matches the instruction tag with the corresponding instruction enum.
     *
     * @param instructionTag The instruction tag to be matched.
     * @return The instruction enum that matches the instruction tag.
     * @throws InvalidInputException If the instruction tag is not recognized.
     */
    private static Instruction matchInstructionTag(String instructionTag) throws InvalidInputException {
        try {
            //convert instruction tag to uppercase to match enum
            return Instruction.valueOf(instructionTag.toUpperCase());
        } catch (IllegalArgumentException e) {
            //thrown if instruction tag does not match any of the enum values
            throw new InvalidInputException(ErrorMessage.UNRECOGNIZED_ERROR);
        }
    }

    /**
     * Creates a command based on the instruction and the information provided.
     * @param instruction The instruction to be executed by the command.
     * @param information The information needed by the command to execute the instruction.
     * @return A command that can execute the instruction with the given information.
     * @throws InvalidInputException If the instruction or the information is invalid.
     */
    private static Command createCommand(Instruction instruction, String information) throws InvalidInputException {
        switch (instruction) {
        case BYE:
            //create and return ExitCommand
            return new ExitCommand();
        case LIST:
            //create and return ListCommand
            return new ListCommand();
        case REMINDER:
            //create and return ReminderCommand
            return new ReminderCommand();
        case MASSDELETE:
            //create and return MassDeleteCommand
            return new MassDeleteCommand();
        case FREE:
            //create and return FindFreeTimeCommand
            return new FindFreeTimeCommand();
        case SORT:
            //create and return FindFreeTimeCommand
            return new SortCommand();
        case HELP:
            //create and return command to mark item using helpDecoder in Decipherer
            return Decipherer.parseHelpCommand(information);
        case MARK:
            //create and return command to mark item using markDecoder in Decipherer
            return Decipherer.parseMarkCommand(information);
        case UNMARK:
            //create and return command to unmark item using unmarkDecoder in Decipherer
            return Decipherer.parseUnmarkCommand(information);
        case DELETE:
            //create and return command to delete item using deleteDecoder in Decipherer
            return Decipherer.parseDeleteCommand(information);
        case TODO:
            //create and return command to create to-do item using todoDecoder in Decipherer
            return Decipherer.parseTodoCommand(information);
        case FIXED:
            //create and return command to create to-do item using todoDecoder in Decipherer
            return Decipherer.parseFixedDurationCommand(information);
        case DEADLINE:
            //create and return command to create deadline item using deadlineDecoder in Decipherer
            return Decipherer.parseDeadlineCommand(information);
        case EVENT:
            //create and return command to create event item using eventDecoder in Decipherer
            return Decipherer.parseEventCommand(information);
        case FIND:
            //create and return command to find item using findDecoder in Decipherer
            return Decipherer.parseFindCommand(information);
        case VIEW:
            //create and return command to view item using viewDecoder in Decipherer
            return Decipherer.parseViewCommand(information);
        case UPDATE:
            //create and return command to update item using updateDecoder in Decipherer
            return Decipherer.parseUpdateCommand(information);
        }
        //if no case matched, return null
        return null;
    }
}
