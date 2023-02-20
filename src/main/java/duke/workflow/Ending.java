package duke.workflow;

import duke.util.TaskList;

/**
 * A more specific implementation of {@code Event}.
 *
 * Part of the workflow where the chatbot say goodbye to
 * the user and the program ends.
 */

public class Ending extends Event {
    private TaskList taskList;

    /**
     * Constructs the {@code Ending} event with an empty
     * list of task. Used when the user does not want to
     * interact with Duke
     */

    public Ending() {
        super(true);
        this.taskList = new TaskList();
    }

    /**
     * Constructs the {@code Ending} event with a specified
     * list of task. Used when the user wants to log off
     * after interacting with Duke.
     */

    public Ending(TaskList taskList) {
        super(true);
        this.taskList = taskList;
    }

    public Event toNextEvent(String nextTask) {
        return this;
    }

    /**
     * Dummy method to implement the abstract class's method.
     */

    public TaskList getTaskList() {
        return this.taskList;
    }

    @Override
    public String toString() {
        return "VERY WELL. THE WORLD IS SAFE FROM YOUR PLAN. FOR NOW";
    }
}
