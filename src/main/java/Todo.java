public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
    public String saveString() {
        return String.format("todo / %s / %s", super.saveString(), this.getDescription());
    }
}
