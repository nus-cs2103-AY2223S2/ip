package task;

public class Todo extends Task{
    public Todo(String name) {
        super(name);
    }

    @Override
    public String getSaveFormat() {
        return "T" + super.getSaveFormat();
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
