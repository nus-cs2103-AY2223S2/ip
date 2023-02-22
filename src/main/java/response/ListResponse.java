package response;

import storage.TaskList;

/**
 * Represents a response to list everything in the to do list
 */
public class ListResponse extends Response {
    /**
     * List everything in the to do list
     * @param taskList The to do list to be printed
     * @return Customised string representation of the to do list
     */
    @Override
    public String exec(TaskList taskList) {
        return taskList.toString();
    }

    /**
     * Custom equals operator to compare ListResponse objects
     * @param obj The other ListResponse object to compare to
     * @return boolean if the two are the same ListResponse
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ListResponse)) {
            return false;
        }
        return true;
    }
}
