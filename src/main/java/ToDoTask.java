public class ToDoTask extends Task {
    public ToDoTask(String title) {
        super(title);
    }

    @Override
    public String toString() {
        String status = this.isDone ? "[x] " : "[ ] ";
        return "[T]" + status + this.title;
    }
}
