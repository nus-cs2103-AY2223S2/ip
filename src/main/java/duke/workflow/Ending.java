package duke.workflow;

import duke.util.TaskList;

public class Ending extends Event {
    TaskList taskList;
    public Ending() {
        super(true);
        this.taskList = new TaskList();
    }

    public Ending(TaskList taskList) {
        super(true);
        this.taskList = taskList;
    }
    public Event toNext() {
        return this;
    }

    public TaskList getTaskList() {
        return this.taskList;
    }

    @Override
    public String toString() {
        return "VERY WELL. THE WORLD IS SAFE FROM YOUR PLAN. FOR NOW";
    }
}