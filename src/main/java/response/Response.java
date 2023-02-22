package response;

import storage.TaskList;

/**
 * Abstract class containing the methods that a Response should have
 */
public abstract class Response {
    public abstract String exec(TaskList taskList);
}
