package response;

import storage.ToDoList;

/**
 * Abstract class containing the methods that a Response should have
 */
public abstract class Response {
    public abstract String exec(ToDoList toDoList);
}
