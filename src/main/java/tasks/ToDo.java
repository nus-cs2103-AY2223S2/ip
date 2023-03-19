package tasks;

/**
 * Class that represents ToDo Task
 */
public class ToDo extends Task {
    public ToDo(String toDo) {
        super(toDo);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
