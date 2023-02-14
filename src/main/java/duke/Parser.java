package duke;

import duke.command.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Represents a parser for input text.
 */
public class Parser {
    /**
     * Returns the command object that the input line represents.
     * @param fullCommand full unparsed command.
     * @return command
     */
    public static Command parse(String fullCommand) {
        String[] parts = fullCommand.split(" ");

        switch (parts[0].toLowerCase()) {
        case "bye":
            return new CommandBye(fullCommand);
        case "list":
            return new CommandList(fullCommand);
        case "undo":
            return new CommandUndo(fullCommand);
        case "mark":
        case "unmark":
            if (parts.length != 2) {
                System.out.println("invalid\n");
                return null;
            }
            int index = Integer.parseInt(parts[1]) - 1;

            if (parts[0].equalsIgnoreCase("mark")) {
                return new CommandMark(fullCommand, index, true);
            } else if (parts[0].equalsIgnoreCase("unmark")) {
                return new CommandMark(fullCommand, index, false);
            } else {
                return null;
            }
        case "delete":
            if (parts.length != 2) {
                System.out.println("invalid\n");
                return null;
            }
            index = Integer.parseInt(parts[1]) - 1;
            return new CommandDelete(fullCommand, index);
        case "find":
            if (parts.length != 2) {
                System.out.println("invalid\n");
                return null;
            }
            return new CommandFind(fullCommand, parts[1]);
        default:
            Task t = null;

            try {
                if (parts[0].equalsIgnoreCase("todo")) {
                    if (fullCommand.length() == 5) {
                        return new CommandError(fullCommand, "OOPS!!! The description of a todo cannot be empty.");
                    }
                    t = new Todo(fullCommand.substring(5));
                } else if (parts[0].equalsIgnoreCase("deadline")) {
                    index = fullCommand.indexOf("/by");
                    if (index != -1) {
                        t = new Deadline(fullCommand.substring(9, index), fullCommand.substring(index + 4));
                    } else {
                        return new CommandError(fullCommand, "OOPS!!! Can't find a /by time for a deadline.");
                    }
                } else if ((parts[0].equalsIgnoreCase("event"))) {
                    int indexFrom = fullCommand.indexOf("/from");
                    int indexTo = fullCommand.indexOf("/to");
                    if (indexFrom != -1 && indexTo != -1) {
                        t = new Event(fullCommand.substring(6, indexFrom),
                                fullCommand.substring(indexFrom + 6, indexTo - 1),
                                fullCommand.substring(indexTo + 4));
                    } else {
                        return new CommandError(fullCommand, "OOPS!!! Can't find a /from or /to time for an event.");
                    }
                } else {
                    return new CommandError(fullCommand, "OOPS!!! I'm sorry, but I don't know what that means :-(");
                }

                return new CommandTask(fullCommand, t);
            } catch (DukeException ex) {
                System.out.println(ex);
            }
        }
        return null;
    }
}
