package duke.util;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.DukeException;
import duke.command.ByeCommand;
import duke.command.CheckCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.SaveCommand;
import duke.command.TodoCommand;
import duke.task.TaskList;

/**
 * The Parser class deals with making sense of the user command.
 * @author Junyi
 */
public class Parser {

    /* TaskList instance of tasks */
    private final TaskList taskList;

    /* Storage instance of Duke */
    private final Storage storage;

    /* Ui instance of Duke */
    private final Ui ui;

    private enum DukeCommand {
        bye,
        todo,
        deadline,
        event,
        list,
        mark,
        unmark,
        delete,
        save,
        check,
        find
    }

    /**
     * Constructor for Parser.
     * @param taskList TaskList of Duke's tasks.
     * @param storage Storage instance of Duke.
     * @param ui Ui instance of Duke.
     */
    public Parser(TaskList taskList, Storage storage, Ui ui) {
        this.taskList = taskList;
        this.storage = storage;
        this.ui = ui;
    }

    /**
     * Validate the presence of args after a command.
     * Does not precisely check validity of args for the command.
     * @param cmd User command.
     * @throws DukeException Exception thrown if no args were given.
     */
    private void validateNotEmptyArgs(String cmd) throws DukeException {
        if (cmd.split(" ").length <= 1) {
            throw new DukeException("You did not specify any details...");
        }
    }

    /**
     * Validate that a task description was given.
     * @param cmd User command.
     * @throws DukeException Exception thrown if no args were given or task description is empty.
     */
    private void validateHasTaskDescription(String cmd) throws DukeException {
        String[] args = cmd.split(" ");
        if (args.length <= 1 || args[1].charAt(0) == '/') {
            throw new DukeException("What is a task without a description good for?");
        }
    }

    /**
     * Validate that index is within current set of tasks.
     * @param index Index of the task, 0-indexed.
     * @throws DukeException Exception thrown if index is invalid.
     */
    private void validateTaskIndex(Integer index) throws DukeException {
        if (index < 0 || index >= taskList.size()) {
            throw new DukeException("No such task!");
        }
    }

    /**
     * Validate that a parameter has been provided by the user.
     * @param cmd The command given by the user.
     * @param param The mentioned parameter that must exist.
     * @throws DukeException Exception thrown if the parameter is not provided.
     */
    private void validateParameterExists(String cmd, String param) throws DukeException {
        if (cmd.split(" " + param + " ").length < 2) {
            throw new DukeException(String.format("Parameter %s not given...", param));
        }
    }

    /**
     * Returns a Command instance corresponding to the user input.
     * @param cmd User input command.
     * @return A command instance.
     * @throws DukeException Thrown when user command has a format issue.
     */
    public Command parseUserCommand(String cmd) throws DukeException {
        String firstCmd = cmd.split(" ")[0];
        int taskIndex;
        Command command;

        try {
            switch(DukeCommand.valueOf(firstCmd)) {
            case bye:
                command = new ByeCommand();
                break;

            case todo:
                validateNotEmptyArgs(cmd);
                validateHasTaskDescription(cmd);
                String description = cmd.substring(5);

                command = new TodoCommand(taskList, description, ui);
                break;

            case deadline:
                validateNotEmptyArgs(cmd);
                validateHasTaskDescription(cmd);
                validateParameterExists(cmd, "/by");
                String deadlineArgs = cmd.substring(9);

                String deadlineDesc = deadlineArgs.split(" /by ")[0];
                LocalDate deadlineBy = LocalDate.parse(deadlineArgs.split(" /by ")[1]);

                command = new DeadlineCommand(taskList, deadlineDesc, deadlineBy, ui);
                break;

            case event:
                validateNotEmptyArgs(cmd);
                validateHasTaskDescription(cmd);
                validateParameterExists(cmd, "/from");
                validateParameterExists(cmd, "/to");
                String eventArgs = cmd.substring(6);

                String[] firstSplit = eventArgs.split(" /from ");
                String[] secondSplit = firstSplit[1].split(" /to ");

                String eventDesc = firstSplit[0];
                LocalDate eventFrom = LocalDate.parse(secondSplit[0]);
                LocalDate eventBy = LocalDate.parse(secondSplit[1]);

                command = new EventCommand(taskList, eventDesc, eventFrom, eventBy, ui);
                break;

            case check:
                validateNotEmptyArgs(cmd);
                String dueArgs = cmd.substring(6);
                LocalDate targetDate = LocalDate.parse(dueArgs);

                command = new CheckCommand(taskList, targetDate);
                break;

            case list:
                command = new ListCommand(taskList);
                break;

            case mark:
                validateNotEmptyArgs(cmd);
                taskIndex = Integer.parseInt(cmd.split(" ")[1]) - 1;
                validateTaskIndex(taskIndex);

                command = new MarkCommand(taskList, taskIndex, true);
                break;

            case unmark:
                validateNotEmptyArgs(cmd);
                taskIndex = Integer.parseInt(cmd.split(" ")[1]) - 1;
                validateTaskIndex(taskIndex);

                command = new MarkCommand(taskList, taskIndex, false);
                break;

            case delete:
                validateNotEmptyArgs(cmd);
                taskIndex = Integer.parseInt(cmd.split(" ")[1]) - 1;
                validateTaskIndex(taskIndex);

                command = new DeleteCommand(taskList, taskIndex, ui);
                break;

            case save:
                command = new SaveCommand(taskList, storage, ui);
                break;

            case find:
                validateNotEmptyArgs(cmd);
                String keywords = cmd.substring(5);

                command = new FindCommand(taskList, keywords.split(" "));
                break;

            default:
                throw new DukeException("Arii does not recognise this command...");
            }

        } catch (DateTimeParseException e) {
            throw new DukeException("That's not a date! Use the format: yyyy-mm-dd");
        } catch (NumberFormatException e) {
            throw new DukeException("That's not a number! Go count your numbers before trying again.");
        } catch (IllegalArgumentException e) {
            throw new DukeException("Arii does not recognise this command...");
        }
        return command;
    }
}
