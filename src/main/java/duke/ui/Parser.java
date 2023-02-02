package duke.ui;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.DukeException;
import duke.command.CommandAddDeadline;
import duke.command.CommandAddEvent;
import duke.command.CommandAddTodo;
import duke.command.Command;
import duke.command.CommandDeleteTask;
import duke.command.CommandExit;
import duke.command.CommandFind;
import duke.command.CommandList;
import duke.command.CommandMark;
import duke.command.Operation;

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
                return new CommandExit();
            }

            if (input.equals("list")) {
                return new CommandList();
            }

            // Split string into 2 parts, 1st part is the operation, 2nd part is the description
            String[] command = input.split(" ", 2);

            if (command.length < 2) {
                throw new DukeException("No command description given.");
            }

            Operation op = Operation.valueOf(command[0].toUpperCase()); // Attain the operation
            String description = command[1];

            switch (op) {
            case MARK:
            case UNMARK:
                return markTaskParser(op, description);
            case TODO:
            case DEADLINE:
            case EVENT:
                return addTaskParser(op, description);
            case DELETE:
                return deleteTaskParser(description);
            case FIND:
                return findTaskParser(description);
            default:
                return null; // cannot reach here, as Operation.valueOf throws IllegalArgumentException
            }
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
     * @param op Operation (MARK/UNMARK) input by user.
     * @param index The string representing the index of the task to be marked.
     * @return CommandMark instance.
     * @throws NumberFormatException If format of task index is invalid.
     */
    public static Command markTaskParser(Operation op, String index) throws NumberFormatException {
        boolean isDone = op.equals(Operation.MARK);
        int taskIndex = Integer.parseInt(index); // Throws exception if index is not a valid integer.
        return new CommandMark(taskIndex, isDone);
    }

    /**
     * Parses the command to add a task.
     *
     * @param op Operation (TODO/ DEADLINE/ EVENT) input by user.
     * @param description String representing the task description.
     * @return A Command instance representing the type of task to add.
     * @throws DateTimeParseException If the date format is invalid.
     */
    public static Command addTaskParser(Operation op, String description) throws DateTimeParseException {

        switch (op) {
        case TODO:
            return new CommandAddTodo(description);
        case DEADLINE:
            String[] deadlineString = description.split("/by", 2);
            String deadlineDescription = deadlineString[0].trim();
            LocalDate by = LocalDate.parse(deadlineString[1].trim());

            return new CommandAddDeadline(deadlineDescription, by);
        case EVENT:
            String[] eventString = description.split("/from", 2);
            String eventDescription = eventString[0].trim();

            // Parse the string to get to and from dates of the event
            String[] fromAndTo = eventString[1].split("/to", 2);
            LocalDate from = LocalDate.parse(fromAndTo[0].trim());
            LocalDate to = LocalDate.parse(fromAndTo[1].trim());

            return new CommandAddEvent(eventDescription, from, to);
        default:
            return null;
        }
    }

    /**
     * Parses the command to delete a task.
     *
     * @param index String representing index of the task to be deleted.
     * @return A Command instance to delete a task.
     * @throws NumberFormatException If the date format is invalid.
     */
    public static Command deleteTaskParser(String index) throws NumberFormatException {
        int taskIndex = Integer.parseInt(index); // Throws exception if index is not a valid integer.
        return new CommandDeleteTask(taskIndex);
    }

    /**
     * Parses the command to find tasks matching keyword.
     *
     * @param keyword String representing the keyword to search the tasks for.
     * @return A Command instance to find tasks.
     */
    public static Command findTaskParser(String keyword) {
        return new CommandFind(keyword);
    }
}
