package response;

import storage.TaskList;

/**
 * Represents the response to a FIND input from user
 */
public class FindResponse extends Response {
    private String keyword;

    public FindResponse(String inputContent) {
        this.keyword = inputContent;
    }

    /**
     * Function to execute the find response
     * @param taskList The task list to find the tasks from
     * @return The string response after execution
     */
    @Override
    public String exec(TaskList taskList) {
        // Find tasks using keyword from given task list
        String found = taskList.findMatchingTasks(keyword);
        return found;
    }

    /**
     * Custom equals method to determine the equality of FindResponse objects
     * @param obj the object to compare to
     * @return boolean if the 2 FindResponse objects are equal
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FindResponse)) {
            return false;
        }
        FindResponse that = (FindResponse) obj;
        return this.keyword.equals(that.keyword);
    }
}
