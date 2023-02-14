package duke;

import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.StoreCommand;
import duke.exception.DukeException;
import duke.exception.TaskIndexException;
import duke.task.Task;

public class Parser {
    /**
     * Reads the command string and converts it into a Command object based on the string
     * @param line string that the user has input
     * @return Command object representing what the command that user input
     * @throws DukeException If the command is invalid
     */
    public static Command parse(String line) throws DukeException {
        assert line != null;
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
        case "save":
            return new StoreCommand(inputs[1]);
        default:
            return Task.taskToCommand(line);
        }
    }
}