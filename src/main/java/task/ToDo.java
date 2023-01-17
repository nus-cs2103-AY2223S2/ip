package task;


/** Tasks without any date/time attached to it. */
public class ToDo extends Task {
    public ToDo(String name) {
        super(name);
    }

    protected ToDo(Task task) {
        super(task);
    }


    @Override
    public ToDo mark(boolean isDone) {
        return new ToDo(super.mark(isDone));
    }


    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
