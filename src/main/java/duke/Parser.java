package duke;

import duke.commands.AddDeadlineCommand;
import duke.commands.AddEventCommand;
import duke.commands.AddToDoCommand;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.ExitCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.commands.MarkDoneCommand;
import duke.commands.NotFoundCommand;

/**
 * Parser for commands
 */
public class Parser {
    /**
     * Parses and runs the entered command.
     *
     * @param line line to be parsed
     * @return String  response
     */
    public Command parse(String line) throws TaskException {
        String[] split = line.split(" ");
        String command = split[0];
        switch (command) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "mark": {
            int number = Integer.parseInt(split[1]);
            return new MarkDoneCommand(true, number);
        }
        case "unmark": {
            int number = Integer.parseInt(split[1]);
            return new MarkDoneCommand(false, number);
        }
        case "todo": {
            if (split.length < 2) {
                throw new TaskCreationException("Description cannot be empty");
            }
            return new AddToDoCommand(split[1]);
        }
        case "deadline": {
            split = line.split(" ");
            if (split.length < 4) {
                throw new TaskCreationException("Invalid format");
            }
            return new AddDeadlineCommand(split[1], split[3]);
        }
        case "event": {
            split = line.split(" ");
            if (split.length < 6) {
                throw new TaskCreationException("Invalid format");
            }
            return new AddEventCommand(split[1], split[3], split[5]);
        }
        case "find": {
            if (split.length < 2) {
                throw new TaskCreationException("Invalid format");
            }
            return new FindCommand(split[1]);
        }
        case "delete": {
            if (split.length != 2) {
                throw new TaskDeletionException("Invalid format");
            }
            int itemIndex;
            try {
                itemIndex = Integer.parseInt(split[1]);
                return new DeleteCommand(itemIndex);
            } catch (NumberFormatException e) {
                throw new TaskDeletionException("Not a number");
            }
        }
        default:
            return new NotFoundCommand();
        }
    }

}
