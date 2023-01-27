public class Todo extends Duke.Task {
    public Todo(String content) {
        super(content.substring(5));
    }

    public String toString() {
        return ". [T][" + super.markSign(super.mark) + "] " + super.content;
    }

    public Todo(String content, boolean mark) {
        super(content.substring(5), mark);
    }

    public String printTodo() {
        return "[T]" + " [" + super.markSign(super.mark) + "] " + super.content + "\n";
    }
}