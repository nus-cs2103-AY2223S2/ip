package duke.util;

import duke.DukeException;
import duke.command.*;
import duke.task.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * The Parser class deals with making sense of the user command.
 */
public class Parser {

    private final TaskList taskList;
    private final Storage storage;
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
                    String description = cmd.substring(5);

                    command = new TodoCommand(taskList, description, ui);
                    break;

                case deadline:
                    validateNotEmptyArgs(cmd);
                    String deadlineArgs = cmd.substring(9);

                    // Validation of input
                    if (deadlineArgs.split(" /by ").length < 2) {
                        throw new DukeException("Insufficient details given...");
                    }

                    String deadlineDesc = deadlineArgs.split(" /by ")[0];
                    LocalDate deadlineBy = LocalDate.parse(deadlineArgs.split(" /by ")[1]);

                    command = new DeadlineCommand(taskList, deadlineDesc, deadlineBy, ui);
                    break;

                case event:
                    validateNotEmptyArgs(cmd);
                    String eventArgs = cmd.substring(6);

                    // Validation of input
                    if (eventArgs.split(" /from ").length < 2 || eventArgs.split(" /to ").length < 2) {
                        throw new DukeException("Insufficient details given...");
                    }

                    String eventDesc = eventArgs.split(" /from ")[0];
                    LocalDate eventFrom = LocalDate.parse(eventArgs.split(" /from ")[1].split(" /to ")[0]);
                    LocalDate eventBy = LocalDate.parse(eventArgs.split(" /from ")[1].split(" /to ")[1]);

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
                    String keyword = cmd.substring(5);

                    command = new FindCommand(taskList, keyword);
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
