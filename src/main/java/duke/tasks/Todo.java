package duke.tasks;
public class Todo extends Task {
    private String desc;

    public Todo(int id, String description) {
        super(id);
        desc = description;
    }

    public String description() {
        return desc;
    }

    @Override
    public String toString() {
        String isDone = isCompleted() ? "X" : " ";
        return String.format("[T][%s] %s", isDone, description());
    }
}
