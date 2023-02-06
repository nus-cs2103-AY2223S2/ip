package aqua.usertask;


/** A {@code UserTask} to represent a todo. */
public class UserTodo extends UserTask {
    private final boolean isComplete;


    /**
     * Constructs a {@code UserTask} with the given name while setting the
     * task completion status to {@code false}.
     *
     * @param name - the name of the task.
     */
    public UserTodo(String name) {
        this(name, false);
    }


    /**
     * Constructs a {@code UserTask} with the given parameters.
     *
     * @param name - the name of the task.
     * @param isCompleted - if the task is completed.
     */
    public UserTodo(String name, boolean isComplete) {
        super(name);
        this.isComplete = isComplete;
    }


    @Override
    public UserTodo mark(boolean isComplete) {
        return new UserTodo(getName(), isComplete);
    }


    @Override
    public boolean isComplete() {
        return isComplete;
    }


    @Override
    public String getReloadString() {
        return String.format("todo %s /%s %s",
                getName(),
                TAG_IS_COMPLETE, isComplete);
    }


    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
