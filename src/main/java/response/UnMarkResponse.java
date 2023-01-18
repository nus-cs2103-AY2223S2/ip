package response;

import exception.InvalidArgumentException;
import storage.ToDoList;
import storage.Task;

/**
 * Represents a response to unmark a task in the to do list
 */
public class UnMarkResponse extends Response {
    /**
     * Represents the index of the task to be unmarked
     */
    private Integer idxToMark;

    public UnMarkResponse(String inputContent) {
        try {
            this.idxToMark = Integer.parseInt(inputContent);
        } catch (NumberFormatException e) {
            throw new InvalidArgumentException("Enter a number after unmark!");
        }
    }

    /**
     * Unmarks a task in the to do list specified
     * @param toDoList The to do list specified
     * @return String to print to the console later
     */
    @Override
    public String exec(ToDoList toDoList) {
        Task currTask = toDoList.unmark(idxToMark);
        return String.format("OK, I've marked this task as not done yet:\n\t\t%s", currTask.toString());
    }
}