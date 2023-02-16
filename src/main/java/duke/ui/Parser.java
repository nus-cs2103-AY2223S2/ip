package duke.ui;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import duke.DukeException;
import duke.command.Command;
import duke.command.CommandAddDeadline;
import duke.command.CommandAddEvent;
import duke.command.CommandAddTodo;
import duke.command.CommandDeleteTask;
import duke.command.CommandExit;
import duke.command.CommandFind;
import duke.command.CommandList;
import duke.command.CommandMark;
import duke.command.CommandUpdate;
import duke.command.Operation;
import duke.task.TaskComponent;
import javafx.util.Pair;

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

            /* Split string into 2 parts, 1st part is the operation, 2nd part is the description */
            String[] command = input.split(" ", 2);
            Operation op = Operation.valueOf(command[0].trim().toUpperCase()); // Throws exception if invalid operation.

            if (op.equals(Operation.BYE)) {
                return new CommandExit();
            }

            if (op.equals(Operation.LIST)) {
                return new CommandList();
            }

            boolean isNoDescription = command.length < 2 || command[1].trim().isEmpty();
            if (isNoDescription) {
                throw new DukeException("No command description given.");
            }

            String description = command[1].trim();

            switch (op) {
            case MARK:
            case UNMARK:
                return parseMarkTask(op, description);
            case TODO:
            case DEADLINE:
            case EVENT:
                return parseAddTask(op, description);
            case DELETE:
                return parseDeleteTask(description);
            case FIND:
                return parseFindTask(description);
            case UPDATE:
                return parseUpdateTask(description);
            default:
                assert false : "cannot reach here as Operation::valueOf already throws IllegalArgumentException";
                return null;
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
     * Parses the command to mark/unmark a task.
     *
     * @param op Operation (MARK/UNMARK) input by user.
     * @param index The string representing the index of the task to be marked.
     * @return CommandMark instance.
     * @throws NumberFormatException If format of task index is invalid.
     */
    public static Command parseMarkTask(Operation op, String index) throws NumberFormatException {
        boolean isDone = op.equals(Operation.MARK);
        int taskIndex = Integer.parseInt(index); // Throws NumberFormatException if index is not a valid integer.
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

    public static Command parseAddTask(Operation op, String description) throws DateTimeParseException {

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

            /* Parse the string to get to and from dates of the event */
            String[] fromAndTo = eventString[1].split("/to", 2);
            LocalDate from = LocalDate.parse(fromAndTo[0].trim());
            LocalDate to = LocalDate.parse(fromAndTo[1].trim());

            return new CommandAddEvent(eventDescription, from, to);
        default:
            assert false : "Cannot reach here";
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
    public static Command parseDeleteTask(String index) throws NumberFormatException {
        int taskIndex = Integer.parseInt(index); // Throws NumberFormatException if index is not a valid integer.
        return new CommandDeleteTask(taskIndex);
    }

    /**
     * Parses the command to find tasks matching a list of keywords.
     *
     * @param keywords String representing the keywords to search the tasks for.
     * @return A Command instance to find tasks.
     */
    public static Command parseFindTask(String keywords) {
        return new CommandFind(keywords.split(" "));
    }

    // exception to handle:
    // 1. wrong index format - CHECK
    // 2. index out of bounds - CHECK
    // 3. invalid component - CHECK
    // 4. task has no such component - CHECK
    // 5. input does not have enough components - CHECK?

    /**
     * Parses the command to update a task.
     *
     * @param updateDescription String representing the update information.
     * @return An instance of CommandUpdate.
     * @throws NumberFormatException If the format of index is invalid.
     * @throws DateTimeParseException If the format of the date is invalid.
     * @throws DukeException If any of the following is violated:
     *         If the format of task component is invalid.
     *         If the update component is missing.
     */
    public static Command parseUpdateTask(String updateDescription)
            throws NumberFormatException, DateTimeParseException, DukeException {

        /* Obtains index of task and what to update */
        int limiter = 2;
        String[] indexAndUpdate = updateDescription.split(" ", limiter);

        if (indexAndUpdate.length < limiter) {
            throw new DukeException("Update information missing.");
        }

        int index = Integer.parseInt(indexAndUpdate[0].trim());
        String update = indexAndUpdate[1];

        /* Splits the update information based on the update components */
        String regex = "((?=/description|/by|/from|/to)|(?<=/description|/by|/from|/to))";
        String[] updateComponents = update.split(regex);

        System.out.println(Arrays.toString(updateComponents));

        /* Creates a list of pairs of task component and the updated information */
        List<Pair<TaskComponent, ?>> updateComponentsList = new ArrayList<>();
        for (int i = 0; i < updateComponents.length - 1; i = i + 2) {
            String component = updateComponents[i].trim();
            String updateContent = updateComponents[i + 1].trim();

            switch (component) {
            case "/description":
                updateComponentsList.add(new Pair<>(TaskComponent.DESCRIPTION, updateContent));
                break;
            case "/by":
                LocalDate newBy = LocalDate.parse(updateContent);
                updateComponentsList.add(new Pair<>(TaskComponent.BY, newBy));
                break;
            case "/from":
                LocalDate newFrom = LocalDate.parse(updateContent);
                updateComponentsList.add(new Pair<>(TaskComponent.FROM, newFrom));
                break;
            case "/to":
                LocalDate newTo = LocalDate.parse(updateContent);
                updateComponentsList.add(new Pair<>(TaskComponent.TO, newTo));
                break;
            default:
                throw new DukeException("Invalid task component: " + component);
            }
        }

        if (updateComponentsList.isEmpty()) {
            throw new DukeException("Invalid update command format.");
        }

        return new CommandUpdate(index, updateComponentsList);
    }
}
