package response;

import exception.InvalidArgumentException;
import storage.Task;
import storage.TaskList;

/**
 * Represents a response to mark a task in the to do list
 */
public class MarkResponse extends Response {
    /**
     * Represents the index of the task to be marked
     */
    private Integer idxToMark;

    /**
     * Constructor for the MarkResponse class
     * @param inputContent String that contains the index to mark
     * @throws InvalidArgumentException when a user inputs anything other than an integer
     */
    public MarkResponse(String inputContent) throws InvalidArgumentException {
        try {
            this.idxToMark = Integer.parseInt(inputContent);
        } catch (NumberFormatException e) {
            throw new InvalidArgumentException("Enter a number after mark!");
        }

    }

    /**
     * Marks a task in the to do list specified
     * @param taskList The to do list specified
     * @return String to print to the console later
     */
    @Override
    public String exec(TaskList taskList) {
        Task currTask = taskList.mark(idxToMark);
        return String.format("Nice! I've marked this task as done:\n\t%s", currTask.toString());
    }

    /**
     * Custom equals operator to compare MarkResponse objects
     * @param obj The other MarkResponse object to compare to
     * @return boolean if the two are the same MarkResponse
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MarkResponse)) {
            return false;
        }
        MarkResponse that = (MarkResponse) obj;
        return idxToMark == that.idxToMark;
    }
}
