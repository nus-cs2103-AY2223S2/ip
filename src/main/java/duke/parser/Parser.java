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

/**
 * This class allows commands to be stored and checked, whether they are in the system
 * and its operations can be carried out.
 */
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

    /**
     * This method parses the string input and breaks it down to its respective inputs, messages, dates.
     *
     * @param input String input, where they can indicate "todo work" etc, which is considered an input.
     * @return Command - A command object of the indicated type will be returned
     * @throws IllegalArgumentException - Error that the command does not exists.
     */
    public static Command parse(String input) throws IllegalArgumentException {
<<<<<<< .merge_file_dOkbVn
        String[] input2 = input.split(" ");
        Type t = Type.valueOf(input2[0].toLowerCase());
        switch (t) {
=======
        String[] inputStrings = input.split(" ");
        Type t = Type.valueOf(inputStrings[0].toLowerCase());
        switch(t) {
>>>>>>> .merge_file_tqb3b1
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