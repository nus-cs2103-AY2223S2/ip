package aqua.logic.parser;

import java.util.Scanner;

import aqua.exception.SyntaxException;
import aqua.logic.CommandLineInput;
import aqua.logic.command.Command;


/** A parser to parse a String into a {@code CommandLineInput}. */
public class CommandLineInputParser implements Parser<CommandLineInput> {
    private final ArgumentParser argumentParser;

    /**
     * Constructs a {@code CommandLineInputParser} that uses the given
     * {@code ArgumentParser} to parse arguments.
     *
     * @param argumentParser - the {@code ArgumentParser} to use.
     */
    public CommandLineInputParser(ArgumentParser argumentParser) {
        this.argumentParser = argumentParser;
    }


    @Override
    public CommandLineInput parse(String input) throws SyntaxException {
        String argString = "";
        Command command;

        try (Scanner scanner = new Scanner(input)) {
            if (scanner.hasNext()) {
                command = Command.valueOf(scanner.next().toUpperCase());

                if (scanner.hasNext()) {
                    argString = scanner.nextLine().strip();
                }
            } else {
                throw new SyntaxException("Empty input");
            }
        } catch (IllegalArgumentException illArgEx) {
            throw new SyntaxException("I do not know what that command is suppose to do");
        }

        return new CommandLineInput(command, argumentParser.parse(argString));
    }
}
