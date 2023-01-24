package duke.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.command.*;
import duke.exception.DukeException;
import duke.exception.InvalidInputException;

/**
* A parser that parse the input String into a Duke Instruction with respective information encapsulated.
*/
public class Parser {
    private static final String UNRECOGNIZED_ERROR = "OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
            "Please type in \"help\" to check all available commands.";
    private static final String EMPTY_ERROR = "OOPS!!! The instruction cannot be empty";

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
        SEARCH,
        UPDATE;
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
        //@@author Yufannnn-reused
        //Reused from https://github.com/wweqg/ip/blob/master/src/main/java/duke/parser/Parser.java
        //with minor modification, it is a pretty clean and concise regular expression for general instructions
        Matcher instructionExtractor = Pattern
                .compile("(?<instructionTag>\\S++)(?<information>.*)").matcher(input.strip());
        //@@author

        if (!instructionExtractor.matches()) {
            throw new InvalidInputException(EMPTY_ERROR);
        }
        String instructionTag = instructionExtractor.group("instructionTag").strip();
        String information = instructionExtractor.group("information").strip();

        try {
            Instruction instruction = Instruction.valueOf(instructionTag.toUpperCase());
            switch (instruction) {
                case BYE:
                    return new ExitCommand();
                case LIST:
                    return new ListCommand();
                case REMINDER:
                    return new ReminderCommand();
                case HELP:
                    return new HelpCommand();
                case MASSDELETE:
                    return new MassDeleteCommand();
                case MARK:
                    return Decipherer.markDecoder(information);
                case UNMARK:
                    return Decipherer.unmarkDecoder(information);
                case DELETE:
                    return Decipherer.deleteDecoder(information);
                case TODO:
                    return Decipherer.todoDecoder(information);
                case DEADLINE:
                    return Decipherer.deadlineDecoder(information);
                case EVENT:
                    return Decipherer.eventDecoder(information);
                case FIND:
                    return Decipherer.findDecoder(information);
                case SEARCH:
                    return Decipherer.searchDecoder(information);
                case UPDATE:
                    return Decipherer.updateDecoder(information);
            }
        } catch (IllegalArgumentException e) {
            throw new InvalidInputException(UNRECOGNIZED_ERROR);
        }
        return null;
    }
}
