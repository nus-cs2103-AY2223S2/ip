package Week2.src.main;

/**
 * A subclass of Task
 * It is a default class of tasks that only contains content information.
 */
public class Todo extends Task {

    /**
     * Todo constructor
     * It only contains the content of the task that user has entered.
     * @param content
     */
    Todo(String content) {
        super(content);
    }

    /**
     * It overrides toString() method to change information to a string format.
     * @return String format of task data
     */
    @Override
    public String toString() {
        return "[T][" + this.getDone() + "] " +this.content;
    }
}
