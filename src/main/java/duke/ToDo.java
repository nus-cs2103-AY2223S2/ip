package duke;

/**
 * Class for ToDo object.
 * Contains description of task to be done.
 * 
 * @author Bryan Tan
 */
public class ToDo extends Task {
    private String task;

    /**
     * Constructor for ToDo object.
     * 
     * @param task Description of task.
     * @return ToDo object.
     */
    public ToDo(String task) {
        super(task);
        this.task = task;
    }

    @Override
    public String getTask() {
        return this.task;
    }
    
    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
