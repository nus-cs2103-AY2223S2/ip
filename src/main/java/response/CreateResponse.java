package response;

import exception.MissingArgumentException;
import storage.ToDoList;
import storage.Todo;

/**
 * Represents a response to create a new task in the to do list
 */
public class CreateResponse extends Response {
    /**
     * Represents the new task to be created
     */
    private String todo;

    /**
     * Creates a response with the specified new task
     * @param todo New task
     */
    public CreateResponse(String todo) {
        this.todo = todo;
    }

    /**
     * Creates a new task in the to do list
     * @param toDoList The to do list to create a new task in
     * @return String to indicate that a new task was created successfully
     */
    @Override
    public String exec(ToDoList toDoList) {
        if (this.todo.equals("")) {
            throw new MissingArgumentException("The description of a to do cannot be empty.");
        }
        Todo newTodo = new Todo(this.todo);
        toDoList.createToDo(newTodo);
        return String.format(
                "Alright! This task has been added into the list:" +
                        "\n\t   %s" +
                        "\n\t Now you have %d task(s) in the list.",
                newTodo,
                toDoList.count());
    }
}
