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
    private static final String unrecognizedError = "OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
            "Please type in \"help\" to check all available commands.";
    private static final String emptyError = "OOPS!!! The instruction cannot be empty";
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
            throw new InvalidInputException(emptyError);
        }
        String instructionTag = instructionExtractor.group("instructionTag").strip();
        String information = instructionExtractor.group("information").strip();

        if (instructionTag.equalsIgnoreCase("bye")) {
            return new ExitCommand();
        } else if (instructionTag.equalsIgnoreCase("list")) {
            return new ListCommand();
        } else if (instructionTag.equalsIgnoreCase("reminder")) {
            return new ReminderCommand();
        } else if (instructionTag.equalsIgnoreCase("help")) {
            return new HelpCommand();
        } else if (instructionTag.equalsIgnoreCase("mark")) {
            return Decipherer.markDecoder(information);
        } else if (instructionTag.equalsIgnoreCase("unmark")) {
            return Decipherer.unmarkDecoder(information);
        } else if (instructionTag.equalsIgnoreCase("delete")) {
            return Decipherer.deleteDecoder(information);
        } else if (instructionTag.equalsIgnoreCase("todo")) {
            return Decipherer.todoDecoder(information);
        } else if (instructionTag.equalsIgnoreCase("deadline")) {
            return Decipherer.deadlineDecoder(information);
        } else if (instructionTag.equalsIgnoreCase("event")) {
            return Decipherer.eventDecoder(information);
        } else if (instructionTag.equalsIgnoreCase("find")) {
            return Decipherer.findDecoder(information);
        } else if (instructionTag.equalsIgnoreCase("search")) {
            return Decipherer.searchDecoder(information);
        } else if (instructionTag.equalsIgnoreCase("update")) {
            return Decipherer.updateDecoder(information);
        }else {
            throw new InvalidInputException(unrecognizedError);
        }
    }
}
