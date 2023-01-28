package Week2.src.main;

/**
 * A subclass of Task
 * It is a default class of tasks that only contains content information.
 * A subclass that extends Task
 * It is a default Task class that only contains the task context information
 */
public class Todo extends Task {

    /**
     * Todo constructor
     * It only contains the content of the task that user has entered.
     * It only contains task context
     * @param content
     */
    Todo(String content) {
        super(content);
    }

    /**
     * It overrides toString() method to change information to a string format.
     * @return String format of task data
     * It overrides toString() method to change the information to a string format.
     * @return A string format of the given task information
     */
    @Override
    public String toString() {
        return "[T][" + this.getDone() + "] " + this.content;
    }
}
