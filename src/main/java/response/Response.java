package response;

import task.TaskList;

/**
 * Represents a response for the application.
 */
public class Response {

    private String message;
    private TaskList tasks;

    /**
     * Constructor for Response object
     * @param message The message stored within the response.
     * @param tasks The list of tasks returned with the response.
     */
    public Response(String message, TaskList tasks) {
        this.message = message;
        this.tasks = tasks;
    }

    @Override
    public String toString() {
        return message + '\n' + (tasks == null ? "" : tasks.toString());
    }

    /**
     * Accessor method for message.
     * @return Returns the message stored within the response.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Accessor method for the returned task list.
     * @return The list of tasks returned with the response.
     */
    public TaskList getTaskList() {
        return tasks;
    }
}