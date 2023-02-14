package duke.command;

import static duke.command.ByeCommand.createByeCommand;
import static duke.command.CheckCommand.createCheckCommand;
import static duke.command.DeadlineCommand.createDeadlineCommand;
import static duke.command.DeleteCommand.createDeleteCommand;
import static duke.command.EventCommand.createEventCommand;
import static duke.command.FindCommand.createFindCommand;
import static duke.command.ListCommand.createListCommand;
import static duke.command.MarkCommand.createMarkCommand;
import static duke.command.SaveCommand.createSaveCommand;
import static duke.command.TodoCommand.createTodoCommand;

import duke.DukeException;
import duke.task.TaskList;
import duke.util.Storage;

/**
 * The command class encapsulates the requests by the user input.
 * @author Junyi
 */
public abstract class Command {

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
     * Returns a Command instance based on the user command.
     * @param cmd Command from the user input.
     * @param userInput Full string of the user input.
     * @param taskList Task List of Duke.
     * @param storage Storage of Duke.
     * @return An instance of Command's subclass.
     * @throws DukeException Thrown if a command has failed to be created.
     */
    public static Command createCommandFromInput(String cmd, String userInput, TaskList taskList, Storage storage)
            throws DukeException {
        switch(DukeCommand.valueOf(cmd)) {
        case bye:
            return createByeCommand();
        case todo:
            return createTodoCommand(userInput, taskList);
        case deadline:
            return createDeadlineCommand(userInput, taskList);
        case event:
            return createEventCommand(userInput, taskList);
        case check:
            return createCheckCommand(userInput, taskList);
        case list:
            return createListCommand(taskList);
        case mark:
            return createMarkCommand(userInput, taskList, true);
        case unmark:
            return createMarkCommand(userInput, taskList, false);
        case delete:
            return createDeleteCommand(userInput, taskList);
        case save:
            return createSaveCommand(userInput, taskList, storage);
        case find:
            return createFindCommand(userInput, taskList);
        default:
            throw new DukeException("Use a command I understand won't you?");
        }
    }

    /**
     * Executes this command and returns true if Duke should exit
     * @return Response from Duke
     * @throws DukeException Thrown on exception caught mid-execution.
     */
    public abstract String execute() throws DukeException;

}
