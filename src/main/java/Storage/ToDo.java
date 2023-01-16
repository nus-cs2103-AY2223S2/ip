package Storage;

public class ToDo extends Task {

    public ToDo(String task) {
        super(task);
    }

    @Override
    public String toString() {
        return "[T]" + status() + getTask();
    }
}
