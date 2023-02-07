package duke.task;

import duke.task.Task;

public class ToDo extends Task {

    String taskDescription;

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
