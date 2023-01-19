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
        return String.format("[T] %s", description());
    }
}
