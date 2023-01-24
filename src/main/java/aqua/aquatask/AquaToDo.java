package aqua.aquatask;


/** An implementation of AquaTask to represent a todo. */
public class AquaToDo extends AquaTask {
    /** If the task is completed. */
    private final boolean isComplete;

    
    /**
     * Constructs an AquaToDo with the given name while setting the task
     * completion status to {@code false}.
     * 
     * @param name - the name of the task.
     */
    public AquaToDo(String name) {
        this(name, false);
    }

    /**
     * Constructs an AquaToDo with the given parameters.
     * 
     * @param name - the name of the task.
     * @param isCompleted - if the task is completed.
     */
    public AquaToDo(String name, boolean isComplete) {
        super(name);
        this.isComplete = isComplete;
    }


    @Override
    public AquaToDo mark(boolean isComplete) {
        return new AquaToDo(this.getName(), isComplete);
    }

    
    @Override
    public boolean isComplete() {
        return this.isComplete;
    }


    @Override
    public String getReloadString() {
        return String.format(
            "todo %s /%s %s",
            getName(),
            IS_COMPLETED_TAG, isComplete
        );
    }


    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
