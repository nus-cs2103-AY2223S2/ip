package duke.parser;

import duke.command.ByeCommand;
import duke.command.EventCommand;
import duke.command.DeadlineCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.command.TodoCommand;
import duke.command.DeleteCommand;
import duke.command.Command;

public class Parser {
    private enum Type {
        todo,
        deadline,
        mark,
        unmark,
        list,
        bye,
        delete,
        event;
    }

    public static Command parse(String input) throws IllegalArgumentException {
        String[] inputStrings = input.split(" ");
        Type t = Type.valueOf(inputStrings[0].toLowerCase());
        switch(t) {
            case todo:
                return new TodoCommand(inputStrings[1]);

            case deadline:
                return new DeadlineCommand(input);

            case mark:
                return new MarkCommand(inputStrings[1]);

            case unmark:
                return new UnmarkCommand(inputStrings[1]);

            case list:
                return new ListCommand();

            case bye:
                return new ByeCommand();

            case delete:
                return new DeleteCommand(inputStrings[1]);

            case event:
                return new EventCommand(input);

            default:
                throw new IllegalArgumentException();
        }
    }
}