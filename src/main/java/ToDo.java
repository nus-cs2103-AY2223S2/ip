public class ToDo extends Task{
    public ToDo(String TaskName) {
        super(TaskName);
    }

    @Override
    public String toString() {
        return "[T]" + "[" + super.getStatus() + "]" + super.toString();
    }

    @Override
    public String toSaveString() {
        return "T" + "=" + super.getStatus() + "=" + super.toSaveString().strip();
    }
}
