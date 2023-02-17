package response;

import java.util.HashMap;
import java.util.Map;

import exception.InvalidArgumentException;
import exception.MissingArgumentException;
import storage.Deadline;
import storage.Event;
import storage.Task;
import storage.TaskList;
import storage.Todo;

/**
 * Represents the response to a SORT input from user
 */
public class SortResponse extends Response {
    private static final Map<String, Class<? extends Task>> TASK_TYPE_MAP = new HashMap<>();

    static {
        TASK_TYPE_MAP.put("todo", Todo.class);
        TASK_TYPE_MAP.put("event", Event.class);
        TASK_TYPE_MAP.put("deadline", Deadline.class);
        // Add more task types here as needed
    }

    private Class<? extends Task> taskType;
    private String order;
    private String typeStr;

    /**
     * Contructor for the Sort response. Parses the user inputs and handles errors as well
     * @param inputContent the user input on what type of events to sort and in what order
     */
    public SortResponse(String inputContent) {
        String[] strArr = inputContent.split(" ", 2);

        // error handling
        if (strArr.length != 2) {
            throw new MissingArgumentException("You don't have the right amount of arguments!"
                    + "Make sure to use 'sort <type of task> <order>'!");
        }

        // assigning the
        this.typeStr = strArr[0].trim();
        this.taskType = TASK_TYPE_MAP.get(typeStr.toLowerCase());
        this.order = strArr[1].trim();

        // more error handling
        if (taskType == null || !(order.equalsIgnoreCase("ascending") || order.equalsIgnoreCase("descending"))) {
            throw new InvalidArgumentException("The arguments you passed in are invalid!"
                    + "The accepted arguments for sort are:"
                    + "\n\t1. task type: todo / deadline / event"
                    + "\n\t2. order: ascending / descending");
        }
    }

    /**
     * Function to execute the find response
     * @param taskList The task list to find the tasks from
     * @return The string response after execution
     */
    @Override
    public String exec(TaskList taskList) {
        // Filter by task type
        TaskList filteredTasks = taskList.filterBy(taskType);

        // Sort
        TaskList sortedFilteredTasks = filteredTasks.sortBy(order);

        // Build the resultant string
        String reply = "I have sorted all " + typeStr + "s in " + order + " order!\n";
        String resTaskString = sortedFilteredTasks.toString();

        // return the res
        return reply + resTaskString;
    }

    /**
     * Custom equals method to determine the equality of SortResponse objects
     * @param obj the object to compare to
     * @return boolean if the 2 SortResponse objects are equal
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SortResponse)) {
            return false;
        }
        SortResponse that = (SortResponse) obj;
        return this.order.equals(that.order) && this.taskType.equals(that.taskType);
    }
}
