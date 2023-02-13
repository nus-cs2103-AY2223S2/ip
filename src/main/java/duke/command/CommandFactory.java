package duke.command;

import duke.command.exceptions.InvalidParameterError;
import duke.interfaces.Command;
import duke.model.TaskModel;
import duke.view.cli.TaskView;

/**
 * A factory to create commands.
 */
public class CommandFactory {
    private final TaskModel taskModel;
    private final TaskView taskView;
    public enum CommandType {
        GREET, BYE, LIST, MARK_DONE, MARK_UNDONE, CREATE_TODO,
        CREATE_DEADLINE, CREATE_EVENT, DELETE_TASK, FIND,
    }

    /**
     * Instantiate a command factory with the provided model and view.
     * @param taskModel Model that stores the task list.
     * @param taskView The current view.
     */
    public CommandFactory(TaskModel taskModel, TaskView taskView) {
        this.taskModel = taskModel;
        this.taskView = taskView;
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
            return new ByeCommand(taskView);
        case LIST:
            if (args.length == 1) {
                return new ListTasksCommand(taskModel, taskView, args[0]);
            } else {
                return new ListTasksCommand(taskModel, taskView);
            }

        case MARK_DONE:
            try {
                int markIndex = Integer.parseInt(args[0]) - 1;
                return new MarkDoneCommand(taskView, taskModel, markIndex);
            } catch (NumberFormatException e) {
                throw new InvalidParameterError("Invalid number provided");
            }

        case MARK_UNDONE:
            try {
                int unmarkIndex = Integer.parseInt(args[0]) - 1; // handle parseInt error
                return new MarkUndoneCommand(taskView, taskModel, unmarkIndex);
            } catch (NumberFormatException e) {
                throw new InvalidParameterError("Invalid number provided");
            }

        case CREATE_TODO:
            return new AddToDoCommand(taskView, taskModel, args[0]);
        case CREATE_DEADLINE:
            return new AddDeadlineCommand(taskView, taskModel, args[0], args[1]);
        case CREATE_EVENT:
            return new AddEventCommand(taskView, taskModel, args[0], args[1], args[2]);
        case DELETE_TASK:
            int indexToDelete = Integer.parseInt(args[0]) - 1;
            return new DeleteTaskCommand(taskView, taskModel, indexToDelete);
        case FIND:
            return new FindTaskCommand(taskModel, taskView, args[0]);
        default:
            // in case we add more to CommandType and forget to add here
            return null;
        }
    }
}
