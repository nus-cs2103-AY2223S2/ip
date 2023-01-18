package response;

import exception.InvalidArgumentException;
import storage.ToDoList;
import storage.Task;

/**
 * Represents a response to delete a task in the to do list
 */
public class DeleteResponse extends Response {
    /**
     * Represents the index of the task to be deleted
     */
    private Integer idxToMark;

    public DeleteResponse(String inputContent) {
        try {
            this.idxToMark = Integer.parseInt(inputContent);
        } catch (NumberFormatException e) {
            throw new InvalidArgumentException("Enter a number after delete!");
        }
    }

    /**
     * Deletes a task in the to do list specified
     * @param toDoList The to do list specified
     * @return String to print to the console later
     */
    @Override
    public String exec(ToDoList toDoList) {
        Task removedTask = toDoList.delete(idxToMark);
        return String.format(
                "Noted. I've removed this task:" +
                        "\n\t   %s" +
                        "\n\t Now you have %d task(s) in the list.",
                removedTask,
                toDoList.count());
    }
}