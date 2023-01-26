public class Todo extends Duke.Task {
    public Todo(String content) {
        super(content.substring(5));
    }

    public String toString() {
        String sign = "";
        return ". [T][" + super.markSign(super.mark) + "] " + super.content;
    }

    public String printTodo() {
        return "TODO" + " [" + super.markSign(super.mark) + "] " + super.content + "\n";
    }
}