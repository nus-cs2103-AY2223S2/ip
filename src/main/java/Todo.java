public class Todo extends Task {
    private String desc;

    public Todo(String description) {
        desc = description;
    }

    public String description() {
        return desc;
    }
}
