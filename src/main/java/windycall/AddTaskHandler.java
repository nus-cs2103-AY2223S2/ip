package windycall;

import java.util.List;

public abstract class AddTaskHandler extends OperationHandler {

    /**
     * Adds a new type of task to tasks ArrayList while possibly throwing
     * errors from user invalid input.
     *
     * @param message message input by users
     * @throws WindyCallException If user input command is invalid
     */
    public static String addTask(String message, List<Task> tasks, Storage storage) throws WindyCallException {
        String[] parts = message.split(" ");

        Task newTask;
        if (parts[0].equals("todo")) {
            newTask = AddTodoHandler.handleAddTodo(message);
        } else if (parts[0].equals("deadline")) {
            newTask = AddDeadlineHandler.handleAddDeadline(message);
        } else if (parts[0].equals("event")) {
            newTask = AddEventHandler.handleAddEvent(message);
        } else {
            throw new WindyCallException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        String returnedMessage = "Got it. I've added this task:\n";
        returnedMessage += newTask + "\n";
        tasks.add(newTask);
        storage.handleTaskChange(tasks);
        returnedMessage += "Now you have " + tasks.size() + " tasks in the list.";
        return returnedMessage;
    }

}
