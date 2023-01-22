public class ToDo extends Task{
    public ToDo(String TaskName) {
        super(TaskName);
    }

    @Override
    public String toString() {
        return "[T]" + "[" + super.getStatus() + "]" + super.toString();
    }
}
