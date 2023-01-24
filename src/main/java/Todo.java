public class Todo extends Task {

    /** 
     * A public constructor to initialize Todo instance.
     * 
     * @param task Task name.
     */
    Todo(String task) {
        super(task);
    }

    /** 
     * Returns the description of Todo.
     * 
     * @return Todo description.
     */
    @Override
    public String getDescription() {
        return "todo " + super.getTaskName();
    }

    /** 
     * Returns the string representation of the task.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}