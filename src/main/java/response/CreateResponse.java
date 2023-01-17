package response;

import response.Response;
import storage.ToDoList;

public class CreateResponse extends Response {
    private String todo;

    public CreateResponse(String todo) {
        this.todo = todo;
    }

    @Override
    public String exec(ToDoList toDoList) {
        toDoList.createToDo(this.todo);
        return String.format("Alright! This task has been added into the list: %s", this.todo);
    }
}
