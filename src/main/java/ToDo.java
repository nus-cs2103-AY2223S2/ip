public class ToDo extends Task {

    public ToDo(String command) {
        super(command);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String taskToData() {
        int done = isDone() ? 1 : 0;
        String task = getTask();
        return String.format("[T] | %d | %s",
                done,
                task);
    }
}
