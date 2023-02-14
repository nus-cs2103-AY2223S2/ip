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
import duke.commands.SetPriorityCommand;
import duke.task.Task;

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
            return new AddToDoCommand(line.substring(5));
        }
        case "deadline": {
            split = line.substring(9).split("/by");
            if (split.length < 2) {
                throw new TaskCreationException("Invalid format");
            }
            return new AddDeadlineCommand(split[0].trim(), split[1].trim());
        }
        case "event": {
            try {
                split = line.substring(6).split("/from");
                String desc = split[0].trim();
                split = split[1].split("/to");
                String from = split[0].trim();
                String to = split[1].trim();
                return new AddEventCommand(desc, from, to);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new TaskCreationException("Invalid format");
            }
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
        case "priority": {
            if (split.length != 3) {
                throw new TaskDeletionException("Invalid format");
            }
            try {
                return new SetPriorityCommand(Integer.parseInt(split[1]) - 1, Task.Priority.valueOf(split[2]));
            } catch (NumberFormatException e) {
                throw new TaskException("Not a number");
            } catch (IllegalArgumentException e) {
                throw new TaskException("Invalid priority");
            }
        }
        default:
            return new NotFoundCommand();
        }
    }

}
