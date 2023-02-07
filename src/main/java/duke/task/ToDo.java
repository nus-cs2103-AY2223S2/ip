package duke.task;

import duke.task.Task;

/**
 * Class for task to be done
 */
public class ToDo extends Task {

    String taskDescription;

    /**
     * Constructor for To-Do Task
     * @param taskString user taskString input
     */
    public ToDo(String taskString) {
        super(taskString.substring(5));

        taskDescription = taskString.substring(5);
    }

    @Override
    public String getTask() {
        return this.taskDescription;
    }


    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
