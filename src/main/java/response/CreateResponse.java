package response;

import exception.MissingArgumentException;
import storage.TaskList;
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
     * @param taskList The to do list to create a new task in
     * @return String to indicate that a new task was created successfully
     */
    @Override
    public String exec(TaskList taskList) throws MissingArgumentException {
        if (this.todo.equals("")) {
            throw new MissingArgumentException("The description of a todo cannot be empty.");
        }
        Todo newTodo = new Todo(this.todo);
        taskList.createToDo(newTodo);
        return String.format(
                "Alright! This task has been added into the list:"
                        + "\n\t%s"
                        + "\nNow you have %d task(s) in the list.",
                newTodo,
                taskList.count());
    }

    /**
     * Custom equals operator to compare CreateResponse objects
     * @param obj The other CreateResponse object to compare to
     * @return boolean if the two are the same CreateResponse
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CreateResponse)) {
            return false;
        }
        CreateResponse that = (CreateResponse) obj;
        return this.todo.equals(that.todo);
    }
}
