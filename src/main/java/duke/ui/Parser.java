package duke.ui;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.DukeException;
import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddTodoCommand;
import duke.command.Command;
import duke.command.DeleteTaskCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListTasksCommand;
import duke.command.MarkTaskCommand;
import duke.command.Operation;
import duke.command.UnmarkTaskCommand;

/**
 * Represents a parser to process user inputs.
 */
public class Parser {

    /**
     * Parses the user input into a meaningful command.
     *
     * @param input User input.
     * @return Command instance derived from the user input.
     * @throws DukeException If any of the following is violated:
     *         If no command description is given.
     *         If command given is not valid.
     *         If the date format is not valid.
     *         If format of task index is invalid.
     */
    public static Command parse(String input) throws DukeException {

        try {
            if (input.equals("bye")) {
                return new ExitCommand();
            }

            // Split strings into 2, first part is the operation, 2nd part is the description
            String[] command = input.split(" ", 2);
            Operation op = Operation.valueOf(command[0].toUpperCase());

            if (!op.equals(Operation.LIST) && command.length < 2) {
                throw new DukeException("No command description given.");
            }

            switch (op) {
            case MARK:
                return markTaskParser(input);
            case UNMARK:
                return unmarkTaskParser(input);
            case TODO:
            case DEADLINE:
            case EVENT:
                return addTaskParser(input);
            case LIST:
                return new ListTasksCommand();
            case DELETE:
                return deleteTaskParser(input);
            case FIND:
                return findTaskParser(input);
            default:
            }

            return null; // cannot reach here, as duke.command.Operation.valueOf throws IllegalArgumentException
        } catch (NumberFormatException e) {
            throw new DukeException("Task must be referenced by its index.");
        } catch (IllegalArgumentException e) {
            throw new DukeException("Invalid command.");
        } catch (DateTimeParseException e) {
            throw new DukeException("Wrong date format given.");
        }
    }

    /**
     * Parses the mark command input.
     *
     * @param input User input.
     * @return MarkTaskCommand instance.
     * @throws NumberFormatException If format of task index is invalid.
     */
    public static Command markTaskParser(String input) throws NumberFormatException {
        String[] command = input.split(" ", 2);
        int taskIndex = Integer.parseInt(command[1]); // Throws exception if not valid string as id
        return new MarkTaskCommand(taskIndex);
    }

    /**
     * Parses the unmark command input.
     *
     * @param input User input.
     * @return UnmarkTaskCommand instance.
     * @throws NumberFormatException If format of task index is invalid.
     */
    public static Command unmarkTaskParser(String input) throws NumberFormatException {
        String[] command = input.split(" ", 2);
        int taskIndex = Integer.parseInt(command[1]); // Throws exception if not valid string as id
        return new UnmarkTaskCommand(taskIndex);
    }

    /**
     * Parses the command to add a task.
     *
     * @param input User input.
     * @return A Command instance representing the type of task to add.
     * @throws DateTimeParseException If the date format is invalid.
     */
    public static Command addTaskParser(String input) throws DateTimeParseException {
        String[] command = input.split(" ", 2);
        Operation op = Operation.valueOf(command[0].toUpperCase());
        String description = command[1];

        switch (op) {
        case TODO:
            return new AddTodoCommand(description);
        case DEADLINE:
            String[] deadlineString = description.split("/by", 2);
            String deadlineDescription = deadlineString[0].trim();
            LocalDate by = LocalDate.parse(deadlineString[1].trim());

            return new AddDeadlineCommand(deadlineDescription, by);
        case EVENT:
            String[] eventString = description.split("/from", 2);
            String eventDescription = eventString[0].trim();

            // Parse the string to get to and from dates of the event
            String[] fromAndTo = eventString[1].split("/to", 2);
            LocalDate from = LocalDate.parse(fromAndTo[0].trim());
            LocalDate to = LocalDate.parse(fromAndTo[1].trim());

            return new AddEventCommand(eventDescription, from, to);
        default:
        }

        return null;
    }

    /**
     * Parses the command to delete a task.
     *
     * @param input User input.
     * @return A Command instance to delete a task.
     * @throws NumberFormatException If the date format is invalid.
     */
    public static Command deleteTaskParser(String input) throws NumberFormatException {
        String[] command = input.split(" ", 2);
        int taskIndex = Integer.parseInt(command[1]); // Throws exception if not valid string as id
        return new DeleteTaskCommand(taskIndex);
    }

    /**
     * Parses the command to find tasks matching keyword.
     *
     * @param input User input.
     * @return A Command instance to find tasks.
     */
    public static Command findTaskParser(String input) {
        String[] command = input.split(" ", 2);
        String keyword = command[1];
        return new FindCommand(keyword);
    }
}
