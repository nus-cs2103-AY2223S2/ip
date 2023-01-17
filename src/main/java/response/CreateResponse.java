package response;

import response.Response;
import storage.ToDoList;

public class CreateResponse extends Response {
    private String todo;

    public CreateResponse(String todo) {
        this.todo = todo;
    }

    @Override
    public String getMessage(ToDoList toDoList) {
        toDoList.createToDo(this.todo);
        return String.format("Alright! This task has been added into the list: %s", this.todo);
    }
}
