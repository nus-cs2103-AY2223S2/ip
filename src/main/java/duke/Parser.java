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

        if (fullCommand.equalsIgnoreCase("bye")) {
            return new CommandBye(fullCommand);
        } else if (fullCommand.equalsIgnoreCase("list")) {
            return new CommandList(fullCommand);
        } else if (fullCommand.startsWith("mark") || fullCommand.startsWith("unmark")) {
            String[] parts = fullCommand.split(" ");
            if (parts.length != 2) {
                System.out.println("invalid\n");
                return null;
            }
            int index = Integer.parseInt(parts[1]) - 1;

            if (parts[0].equalsIgnoreCase("mark")) {
                return new CommandMark(fullCommand, index, true);
            } else if (parts[0].equalsIgnoreCase("unmark")) {
                return new CommandMark(fullCommand, index, false);
            }
        } else if (fullCommand.startsWith("delete")) {
            String[] parts = fullCommand.split(" ");
            if (parts.length != 2) {
                System.out.println("invalid\n");
                return null;
            }
            int index = Integer.parseInt(parts[1]) - 1;
            return new CommandDelete(fullCommand, index);
        } else if (fullCommand.startsWith("find")) {
            String[] parts = fullCommand.split(" ");
            if (parts.length != 2) {
                System.out.println("invalid\n");
                return null;
            }
            return new CommandFind(fullCommand, parts[1]);
        } else {
            String[] parts = fullCommand.split(" ");
            assert parts.length > 0 : "command should be non empty";
            Task t = null;

            try {
                if (parts[0].equalsIgnoreCase("todo")) {
                    if (fullCommand.length() == 5) {
                        throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                    }
                    t = new Todo(fullCommand.substring(5));
                } else if (parts[0].equalsIgnoreCase("deadline")) {
                    int index = fullCommand.indexOf("/by");
                    if (index != -1) {
                        t = new Deadline(fullCommand.substring(9, index), fullCommand.substring(index + 4));
                    } else {
                        throw new DukeException("OOPS!!! Can't find a /by time for a deadline.");
                    }
                } else if ((parts[0].equalsIgnoreCase("event"))) {
                    int indexFrom = fullCommand.indexOf("/from");
                    int indexTo = fullCommand.indexOf("/to");
                    if (indexFrom != -1 && indexTo != -1) {
                        t = new Event(fullCommand.substring(6, indexFrom),
                                fullCommand.substring(indexFrom + 6, indexTo - 1),
                                fullCommand.substring(indexTo + 4));
                    } else {
                        throw new DukeException("OOPS!!! Can't find a /from or /to time for an event.");
                    }
                } else {
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }

                return new CommandTask(fullCommand, t);
            } catch (DukeException ex) {
                System.out.println(ex);
            }
        }
        return null;
    }
}
