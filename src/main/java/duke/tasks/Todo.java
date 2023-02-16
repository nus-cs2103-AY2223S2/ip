package duke.tasks;

public class Todo extends Task {
    public Todo(String content) {
        super(content.substring(5));
    }

    public String toString() {
        return ". [T][" + super.markSign(super.isMark) + "] " + super.content;
    }

    public Todo(String content, boolean isMark) {
        super(content, isMark);
    }

    public String printRecord() {
        return "[T]" + " [" + super.markSign(super.isMark) + "] " + super.content + "\n";
    }
}