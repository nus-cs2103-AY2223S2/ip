package aqua.logic.parser;

import java.util.Scanner;

import aqua.exception.IllegalSyntaxException;
import aqua.logic.CommandLineInput;
import aqua.logic.command.Command;


public class CommandLineInputParser implements Parser<CommandLineInput> {
    private final ArgumentParser argumentParser;

    public CommandLineInputParser(ArgumentParser argumentParser) {
        this.argumentParser = argumentParser;
    }


    @Override
    public CommandLineInput parse(String input) throws IllegalSyntaxException {
        String argString = "";
        Command command;

        try (Scanner scanner = new Scanner(input)) {
            if (scanner.hasNext()) {
                command = Command.valueOf(scanner.next().toUpperCase());

                if (scanner.hasNext()) {
                    argString = scanner.nextLine().strip();
                }
            } else {
                throw new IllegalSyntaxException("Empty input");
            }
        } catch (IllegalArgumentException illArgEx) {
            throw new IllegalSyntaxException("I do not know what command is suppose to do");
        }

        return new CommandLineInput(command, argumentParser.parse(argString));
    }
}
