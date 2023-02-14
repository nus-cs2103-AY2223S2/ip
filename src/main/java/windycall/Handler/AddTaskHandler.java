package windycall.Handler;

import windycall.OperationType;
import windycall.storage.Storage;
import windycall.task.Task;
import windycall.exception.WindyCallException;
import windycall.parser.Parser;

import java.util.List;

public abstract class AddTaskHandler extends OperationHandler {

    /**
     * Adds a new type of task to tasks ArrayList while possibly throwing
     * errors from user invalid input.
     *
     * @param message message input by users
     * @throws WindyCallException If user input command is invalid
     */
    public static String addTask(String message, List<Task> tasks, Storage storage, Parser parser) throws WindyCallException {
        String[] parts = message.split(" ");

        Task newTask;
        OperationType addTaskType = parser.getAddTaskType(message);
        switch (addTaskType) {
        case TODO:
            newTask = AddTodoHandler.handleAddTodo(message);
            break;
        case DEADLINE:
            newTask = AddDeadlineHandler.handleAddDeadline(message);
            break;
        case EVENT:
            newTask = AddEventHandler.handleAddEvent(message);
            break;
        default:
            throw new WindyCallException("OOPS!!! I'm sorry, but I don't know what that means :-(");
//            break;
        }
        String returnedMessage = "Got it. I've added this task:\n";
        returnedMessage += newTask + "\n";
        tasks.add(newTask);
        storage.handleTaskChange(tasks);
        returnedMessage += "Now you have " + tasks.size() + " tasks in the list.";
        return returnedMessage;
    }

}
