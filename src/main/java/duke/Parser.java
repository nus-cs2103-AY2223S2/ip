package duke;
import duke.command.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;


public class Parser {

    /**
     * Parses a String command and returns a Command object on what the
     * String wants to be executed.
     *
     * @param cmd Text form of command.
     * @return Command to be executed.
     * @throws DukeException If the string command is invalid.
     */
    public static Command parse(String cmd) throws DukeException {
        String[] tokens = cmd.split(" ", 2);
        assert tokens != null : "Command should not be empty";
        String command = tokens[0];

        // List Tasks
        if (command.equals("list")) {
            return new ListCommand();
        }

        // Exit Duke
        if (command.equals("exit")) {
            return new ExitCommand();
        }

        // Remove task
        if (command.equals("remove")) {
            if (tokens.length != 2) {
                throw new DukeException("Please enter an index to remove!");
            }
            try {
                int index = Integer.parseInt(tokens[1]);
                return new DeleteCommand(index);
            } catch (NumberFormatException nfe) {
                throw new DukeException("Index must be an integer!");
            }
        }

        // Show Help
        if (command.equals("help")) {
            if (tokens.length == 1) {
                return new HelpCommand();
            }
            return new HelpCommand(parseHelp(tokens[1]));
        }

        // Mark/Unmark tasks
        if (command.equals("mark")) {
            if (tokens.length != 2) {
                throw new DukeException("Please enter an index to mark!");
            }
            try {
                int index = Integer.parseInt(tokens[1]);
                return new MarkCommand(index);
            } catch (NumberFormatException nfe) {
                throw new DukeException("Index must be an integer!");
            }
        }
        if (command.equals("unmark")) {
            if (tokens.length != 2) {
                throw new DukeException("Please enter an index to unmark!");
            }
            try {
                int index = Integer.parseInt(tokens[1]);
                return new UnmarkCommand(index);
            } catch (NumberFormatException nfe) {
                throw new DukeException("Index must be an integer!");
            }
        }

        // Find task
        if (command.equals("find")) {
            if (tokens.length != 2) {
                throw new DukeException("Please enter a keyword!");
            }
            return new FindCommand(tokens[1]);
        }

        // Adding Tasks
        if (command.equals("todo")) {
            if (tokens.length != 2) {
                throw new DukeException("Please enter a todo task!");
            }
            return new AddCommand(new Todo(tokens[1]));
        }
        if (command.equals("event")) {
            if (tokens.length != 2) {
                throw new DukeException("Please enter a event task!");
            }
            String[] taskTime = tokens[1].split("/at", 2);
            if (taskTime.length != 2) {
                throw new DukeException("Please enter a time for the event!");
            }
            return new AddCommand(new Event(taskTime[0].trim(), taskTime[1]));
        }
        if (command.equals("deadline")) {
            if (tokens.length != 2) {
                throw new DukeException("Please enter a deadline task!");
            }
            String[] taskTime = tokens[1].split("/by", 2);
            if (taskTime.length != 2) {
                throw new DukeException("Please enter a time for the deadline!");
            }
            try {
                Deadline deadline = new Deadline(taskTime[0].trim(), LocalDate.parse(taskTime[1].trim()));
                return new AddCommand(deadline);
            } catch (DateTimeParseException e) {
                throw new DukeException("Please enter a valid date in the format [yyyy-mm-dd]");
            }
        }

        throw new DukeException("Not a valid command.");
    }

    /**
     * Parses the name of a command requested by the HelpCommand.
     *
     * @param input String from HelpCommand request.
     * @return Command of type requested.
     * @throws DukeException if request is not a valid command.
     */
    public static Command parseHelp(String input) throws DukeException {
        input = input.trim();

        // AddCommand
        if (input.equalsIgnoreCase("todo")) {
            return new AddCommand(TaskType.TODO);
        }
        if (input.equalsIgnoreCase("event")) {
            return new AddCommand(TaskType.EVENT);
        }
        if (input.equalsIgnoreCase("deadline")) {
            return new AddCommand(TaskType.DEADLINE);
        }

        if (input.equalsIgnoreCase("remove")) {
            return new DeleteCommand();
        }
        if (input.equalsIgnoreCase("exit")) {
            return new ExitCommand();
        }
        if (input.equalsIgnoreCase("find")) {
            return new FindCommand();
        }
        if (input.equalsIgnoreCase("help")) {
            return new HelpCommand();
        }
        if (input.equalsIgnoreCase("list")) {
            return new ListCommand();
        }

        // Mark/UnmarkCommand
        if (input.equalsIgnoreCase("mark")) {
            return new MarkCommand();
        }
        if (input.equalsIgnoreCase("unmark")) {
            return new UnmarkCommand();
        }

        throw new DukeException("Help: No such command \"" + input + "\"");
    }
}
