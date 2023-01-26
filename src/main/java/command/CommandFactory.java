package command;

import interfaces.Command;
import model.TaskModel;
import view.TaskView;

public class CommandFactory {
    private final TaskModel taskModel;
    private final TaskView taskView;
    public enum CommandType {
        GREET, BYE, LIST, MARK_DONE, MARK_UNDONE, CREATE_TODO, CREATE_DEADLINE, CREATE_EVENT, DELETE_TASK
    }
    public CommandFactory(TaskModel taskModel, TaskView taskView) {
        this.taskModel = taskModel;
        this.taskView = taskView;
    }

    public Command createCommand(CommandType type, String... args) {
        switch (type) {
            case GREET:
                return new GreetingCommand(taskView);
            case BYE:
                return new ByeCommand(taskView);
            case LIST:
                return new ListTasksCommand(taskModel, taskView);
            case MARK_DONE:
                int markIndex = Integer.parseInt(args[0]);
                return new MarkDoneCommand(taskView, taskModel, markIndex);
            case MARK_UNDONE:
                int unmarkIndex = Integer.parseInt(args[0]); // handle parseInt error
                return new MarkUndoneCommand(taskView, taskModel, unmarkIndex);
            case CREATE_TODO:
                return new AddToDoCommand(taskView, taskModel, args[0]);
            case CREATE_DEADLINE:
                return new AddDeadlineCommand(taskView, taskModel, args[0], args[1]);
            case CREATE_EVENT:
                return new AddEventCommand(taskView, taskModel, args[0], args[1], args[2]);
            case DELETE_TASK:
                int indexToDelete = Integer.parseInt(args[0]) - 1; // handle error
                return new DeleteTaskCommand(taskView, taskModel, indexToDelete);
            default:
                // in case we add more to CommandType and forget to add here
                return null;
        }
    }
}
