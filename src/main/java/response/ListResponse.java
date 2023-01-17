package response;

import storage.ToDoList;

public class ListResponse extends Response{
    @Override
    public String exec(ToDoList toDoList) {
        return toDoList.toString();
    }
}
