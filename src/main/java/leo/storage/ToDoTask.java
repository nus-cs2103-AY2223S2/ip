package leo.storage;

public class ToDoTask extends Task {

    public ToDoTask(String task) {
        super(task);
        setType(TaskType.TODO);
    }

    @Override
    public String toString() {
        return typeAndStatus() + getTask();
    }

    @Override
    public String saveFormat() {
        return this + "\n";
    }
}
