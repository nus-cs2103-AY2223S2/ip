public class Todo extends Task {
    private String task;

    public Todo(String task) {
        super(task);
        this.task = task;

    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
