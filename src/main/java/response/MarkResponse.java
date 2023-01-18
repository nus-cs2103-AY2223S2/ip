package response;

import exception.InvalidArgumentException;
import storage.ToDoList;
import storage.Task;

/**
 * Represents a response to mark a task in the to do list
 */
public class MarkResponse extends Response {
    /**
     * Represents the index of the task to be marked
     */
    private Integer idxToMark;

    public MarkResponse(String inputContent) {
        try {
            this.idxToMark = Integer.parseInt(inputContent);
        } catch (NumberFormatException e) {
            throw new InvalidArgumentException("Enter a number after mark/unmark!");
        }

    }

    /**
     * Marks a task in the to do list specified
     * @param toDoList The to do list specified
     * @return String to print to the console later
     */
    @Override
    public String exec(ToDoList toDoList) {
        Task currTask = toDoList.mark(idxToMark);
        return String.format("Nice! I've marked this task as done:\n\t\t%s", currTask.toString());
    }
}
