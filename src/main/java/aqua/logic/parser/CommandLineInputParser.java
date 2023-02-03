package aqua.logic.parser;

import java.util.Scanner;

import aqua.exception.IllegalSyntaxException;
import aqua.logic.CommandLineInput;
import aqua.logic.command.Command;


/** A parser to parse a String into a {@code CommandLineInput}. */
public class CommandLineInputParser implements Parser<CommandLineInput> {
    /** The argument parser to parse argument input. */
    private final ArgumentParser argumentParser;

    /**
     * Constructs a CommandLineInputParser that uses the given ArgumentParser
     * to parse arguments.
     *
     * @param argumentParser - the ArgumentParser to use.
     */
    public CommandLineInputParser(ArgumentParser argumentParser) {
        this.argumentParser = argumentParser;
    }


    @Override
    public CommandLineInput parse(String input) throws IllegalSyntaxException {
        String argString = "";
        Command command;

        try (Scanner scanner = new Scanner(input)) {
            if (scanner.hasNext()) {
                // parse command
                command = Command.valueOf(scanner.next().toUpperCase());

                // parse arguments
                if (scanner.hasNext()) {
                    argString = scanner.nextLine().strip();
                }
            } else {
                throw new IllegalSyntaxException("Empty input");
            }
        } catch (IllegalArgumentException illArgEx) {
            throw new IllegalSyntaxException("I do not know what that command is suppose to do");
        }

        // create and return command line input
        return new CommandLineInput(command, argumentParser.parse(argString));
    }
}
