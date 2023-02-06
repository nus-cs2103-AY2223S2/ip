package duke;

import duke.Command.Command;
import duke.Command.DeleteCommand;
import duke.Command.ExitCommand;
import duke.Command.FindCommand;
import duke.Command.ListCommand;
import duke.Command.MarkCommand;
import duke.Command.FindCommand;
import duke.Exceptions.DukeException;
import duke.Exceptions.TaskIndexException;
import duke.Tasks.Task;

public class Parser {
    public static Command parse(String line) throws DukeException {
        String[] inputs = line.split(" ", 2);
        String strCommand = inputs[0];
        switch (strCommand) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "mark":
            try {
                int index = Integer.parseInt(line.substring(5));
                return new MarkCommand(index);
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                // incorrect syntax
                throw new TaskIndexException();
            }
        case "delete":
            try {
                int index = Integer.parseInt(line.substring(7));
                return new DeleteCommand(index);
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                // incorrect syntax
                throw new TaskIndexException();
            }
        case "find":
            return new FindCommand(inputs[1]);
        default:
            return Task.taskToCommand(line);
        }
    }
}