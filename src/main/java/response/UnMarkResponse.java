package response;

import exception.InvalidArgumentException;
import storage.Task;
import storage.TaskList;

/**
 * Represents a response to unmark a task in the to do list
 */
public class UnMarkResponse extends Response {
    /**
     * Represents the index of the task to be unmarked
     */
    private Integer idxToUnMark;

    /**
     * Constructor for the UnMarkResponse class
     * @param inputContent String that contains the index to unmark
     * @throws InvalidArgumentException when the user enters anything other than an integer
     */
    public UnMarkResponse(String inputContent) throws InvalidArgumentException {
        try {
            this.idxToUnMark = Integer.parseInt(inputContent);
        } catch (NumberFormatException e) {
            throw new InvalidArgumentException("Enter a number after unmark!");
        }
    }

    /**
     * Unmarks a task in the to do list specified
     * @param taskList The to do list specified
     * @return String to print to the console later
     */
    @Override
    public String exec(TaskList taskList) {
        Task currTask = taskList.unmark(idxToUnMark);
        return String.format("OK, I've marked this task as not done yet:\n\t%s", currTask.toString());
    }

    /**
     * Custom equals operator to compare UnMarkResponse objects
     * @param obj The other UnMarkResponse object to compare to
     * @return boolean if the two are the same UnMarkResponse
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof UnMarkResponse)) {
            return false;
        }
        UnMarkResponse that = (UnMarkResponse) obj;
        return idxToUnMark == that.idxToUnMark;
    }
}
