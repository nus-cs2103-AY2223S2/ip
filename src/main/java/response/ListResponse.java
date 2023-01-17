package response;

import storage.ToDoList;

public class ListResponse extends Response{
    @Override
    public String getMessage(ToDoList toDoList) {
        return toDoList.toString();
    }
}
