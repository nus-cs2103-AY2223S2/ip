package task;

public class Todo extends Task {

    Todo(String content) {
        super(content);

    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
