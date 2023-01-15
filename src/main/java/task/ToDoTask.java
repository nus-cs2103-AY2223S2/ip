package task;

public class ToDoTask extends Task{
    public ToDoTask(String task) {
        super(task);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
