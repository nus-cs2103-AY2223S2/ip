package aqua.logic.parser;

import java.util.Scanner;

import aqua.exception.IllegalSyntaxException;
import aqua.logic.CommandLineInput;
import aqua.logic.command.AddDeadlineCommand;
import aqua.logic.command.AddEventCommand;
import aqua.logic.command.AddToDoCommand;
import aqua.logic.command.ByeCommand;
import aqua.logic.command.Command;
import aqua.logic.command.DeleteCommand;
import aqua.logic.command.FilterCommand;
import aqua.logic.command.ListCommand;
import aqua.logic.command.MarkTaskCommand;


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
                command = getCommand(scanner.next());

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


    private static Command getCommand(String input) throws IllegalSyntaxException {
        switch (input) {
            case "list":
                return new ListCommand();
            case "mark":
                return new MarkTaskCommand(true);
            case "unmark":
                return new MarkTaskCommand(false);
            case "todo":
                return new AddToDoCommand();
            case "event":
                return new AddEventCommand();
            case "deadline":
                return new AddDeadlineCommand();
            case "delete":
                return new DeleteCommand();
            case "find":
                return new FilterCommand();
            case "bye":
                return new ByeCommand();
            default:
                throw new IllegalSyntaxException("I do not know what command is suppose to do");
        }
    }
}
