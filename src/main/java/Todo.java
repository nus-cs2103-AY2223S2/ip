public class Todo extends Task{

    public String todo;

    /**
     * Constructor
     * @param description the string description of task
     */
    public Todo(String description) {
        super(description);
        this.todo = description.split(" ",2)[1];
    }

    /**
     * String representation of the task
     * @return string with task details
     */
    public String toString() {
        return "[T]" + "[" + super.getStatusIcon()+ "] " + this.todo;
    }

    /**
     * Get the type of task
     * @return a string "todo"
     */
    public String getType() {
        return "todo";
    }
}
