public class TaskTodo extends Task {
    public TaskTodo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
