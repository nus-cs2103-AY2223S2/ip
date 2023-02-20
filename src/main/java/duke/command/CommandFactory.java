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
    public enum CommandType {
        GREET, BYE, LIST, MARK_DONE, MARK_UNDONE, CREATE_TODO,
        CREATE_DEADLINE, CREATE_EVENT, DELETE_TASK, FIND,
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
                int unmarkIndex = Integer.parseInt(args[0]) - 1; // handle parseInt error
                return new MarkUndoneCommand(taskModel, taskView, unmarkIndex);
            } catch (NumberFormatException e) {
                throw new InvalidParameterError("Invalid number provided");
            }

        case CREATE_TODO:
            return new AddToDoCommand(taskModel, taskView, args[0]);
        case CREATE_DEADLINE:
            return new AddDeadlineCommand(taskModel, taskView, args[0], args[1]);
        case CREATE_EVENT:
            return new AddEventCommand(taskModel, taskView, args[0], args[1], args[2]);
        case DELETE_TASK:
            int indexToDelete = Integer.parseInt(args[0]) - 1;
            return new DeleteTaskCommand(taskModel, taskView, indexToDelete);
        case FIND:
            return new FindTaskCommand(taskModel, taskView, args[0]);
        default:
            // in case we add more to CommandType and forget to add here
            return null;
        }
    }
}
