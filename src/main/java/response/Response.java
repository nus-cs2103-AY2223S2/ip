package response;

import storage.ToDoList;
public abstract class Response {
    public abstract String exec(ToDoList toDoList);
}
