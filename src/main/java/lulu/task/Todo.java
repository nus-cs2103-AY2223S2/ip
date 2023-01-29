package lulu.task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return ("[T]" + super.toString());
    }

    @Override
    public String toMemory() {
        int i = this.isDone ? 1 : 0;
        return ("T`" + i + "`" + this.description + '\n');
    }
}
