package duke.tasks;

public class Todo extends Task {
    public Todo(String content) {
        super(content.substring(5));
    }

    public String toString() {
        return ". [T][" + super.markSign(super.mark) + "] " + super.content;
    }

    public Todo(String content, boolean mark) {
        super(content, mark);
    }

    public String printRecord() {
        return "[T]" + " [" + super.markSign(super.mark) + "] " + super.content + "\n";
    }
}