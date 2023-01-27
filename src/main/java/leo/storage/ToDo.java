package leo.storage;

public class ToDo extends Task {

    public ToDo(String task) {
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
