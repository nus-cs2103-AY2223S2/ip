package duke.command;

import duke.command.exceptions.InvalidParameterError;
import duke.interfaces.Command;
import duke.interfaces.CommandEventListener;
import duke.interfaces.Model;
import duke.interfaces.View;

/**
 * A factory to create commands.
 */
public class CommandFactory {
    private final Model taskModel;
    private final View taskView;

    private final CommandEventListener exitEventListener;

    /**
     * An enum of all the commands that can be created by the CommandFactory.
     */
    public enum CommandType {
        GREET, BYE, LIST, MARK_DONE, MARK_UNDONE, CREATE_TODO,
        CREATE_DEADLINE, CREATE_EVENT, DELETE_TASK, FIND,
        SORT
    }

    /**
     * Instantiate a command factory with the provided model and view.
     * @param taskModel Model that stores the task list.
     * @param taskView The current view.
     */
    public CommandFactory(Model taskModel, View taskView, CommandEventListener exitEventListener) {
        this.taskModel = taskModel;
        this.taskView = taskView;
        this.exitEventListener = exitEventListener;
    }

    /**
     * Creates a command of the provided type with the supplied arguments.
     * @param type The type of command to create.
     * @param args The arguments to the command.
     * @return A new command of the required type instantiated with the supplied arguments.
     * @throws InvalidParameterError If the arguments are improperly formatted.
     */
    public Command createCommand(CommandType type, String... args) throws InvalidParameterError {
        switch (type) {
        case GREET:
            return new GreetingCommand(taskView);
        case BYE:
            return new ByeCommand(taskView, exitEventListener);
        case LIST:
            if (args.length == 1) {
                return new ListTasksCommand(taskModel, taskView, args[0]);
            } else {
                return new ListTasksCommand(taskModel, taskView);
            }

        case MARK_DONE:
            try {
                int markIndex = Integer.parseInt(args[0]) - 1;
                return new MarkDoneCommand(taskModel, taskView, markIndex);
            } catch (NumberFormatException e) {
                throw new InvalidParameterError("Invalid number provided");
            }

        case MARK_UNDONE:
            try {
                int unmarkIndex = Integer.parseInt(args[0]) - 1;
                return new MarkUndoneCommand(taskModel, taskView, unmarkIndex);
            } catch (NumberFormatException e) {
                throw new InvalidParameterError("Invalid number provided");
            }

        case CREATE_TODO:
            assert args[0] != null;
            return new AddToDoCommand(taskModel, taskView, args[0]);
        case CREATE_DEADLINE:
            assert args[0] != null;
            assert args[1] != null;
            return new AddDeadlineCommand(taskModel, taskView, args[0], args[1]);
        case CREATE_EVENT:
            assert args[0] != null;
            assert args[1] != null;
            assert args[2] != null;
            return new AddEventCommand(taskModel, taskView, args[0], args[1], args[2]);
        case DELETE_TASK:
            int indexToDelete = Integer.parseInt(args[0]) - 1;
            return new DeleteTaskCommand(taskModel, taskView, indexToDelete);
        case FIND:
            return new FindTaskCommand(taskModel, taskView, args[0]);
        case SORT:
            return new SortDeadlinesCommand(taskModel, taskView);
        default:
            return null;
        }
    }
}
