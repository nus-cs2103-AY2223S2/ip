package duke.task;

/** A representation of a task with no given deadline. */
public class ToDo extends Task {

    /**
     * Creates a ToDo object with a given name and completion status.
     * 
     * @param name   The name of the object to be created
     * @param isDone The completion status of the object to be created
     */
    public ToDo(String name, boolean isDone) {
        super(name, isDone);
    }

    /**
     * Creates a ToDo object with a given name.
     * 
     * @param name The name of the object to be created
     */
    public ToDo(String name) {
        this(name, false);
    }

    @Override
    protected String getTaskType() {
        return "T";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (obj.getClass() != this.getClass()) {
            return false;
        }
        ToDo task = (ToDo) obj;
        return super.equals(task);
    }
}
