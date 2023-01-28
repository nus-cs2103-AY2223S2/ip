package response;

import exception.InvalidArgumentException;
import storage.Task;
import storage.TaskList;

/**
 * Represents a response to delete a task in the to do list
 */
public class DeleteResponse extends Response {
    /**
     * Represents the index of the task to be deleted
     */
    private Integer idxToDelete;

    /**
     * Constructor for the DeleteResponse class
     * @param inputContent String containing the index to delete
     * @throws InvalidArgumentException when a user inputs anything other than an integer
     */
    public DeleteResponse(String inputContent) throws InvalidArgumentException {
        try {
            this.idxToDelete = Integer.parseInt(inputContent);
        } catch (NumberFormatException e) {
            throw new InvalidArgumentException("Enter a number after delete!");
        }
    }

    /**
     * Deletes a task in the to do list specified
     * @param taskList The to do list specified
     * @return String to print to the console later
     */
    @Override
    public String exec(TaskList taskList) {
        Task removedTask = taskList.delete(idxToDelete);
        return String.format(
                "Noted. I've removed this task:"
                        + "\n\t   %s"
                        + "\n\t Now you have %d task(s) in the list.",
                removedTask,
                taskList.count());
    }
}
