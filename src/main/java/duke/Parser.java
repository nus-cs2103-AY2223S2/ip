package duke;

import duke.Command.Command;
import duke.Command.DeleteCommand;
import duke.Command.ExitCommand;
import duke.Command.ListCommand;
import duke.Command.MarkCommand;
import duke.Command.FindCommand;
import duke.Exceptions.DukeException;
import duke.Exceptions.TaskIndexException;
import duke.Tasks.Task;

public class Parser {
    public static Command parse(String input) throws DukeException {
        String[] inputArray = input.split(" ", 2);
        String inputCommand = inputArray[0];
        switch (inputCommand) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "mark":
                try {
                    int index = Integer.parseInt(input.substring(5));
                    return new MarkCommand(index);
                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    // incorrect syntax
                    throw new TaskIndexException();
                }
            case "delete":
                try {
                    int index = Integer.parseInt(input.substring(7));
                    return new DeleteCommand(index);
                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    // incorrect syntax
                    throw new TaskIndexException();
                }
            case "find":
                return new FindCommand(inputArray[1]);
            default:
                return Task.taskToCommand(input);
        }
    }
}