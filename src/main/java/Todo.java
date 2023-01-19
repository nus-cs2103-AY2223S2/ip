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
     * Returns the string representation of the task.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}