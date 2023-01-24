package task;
public class ToDo extends Task{

    public ToDo(String task) {
        super(task, false);
    }

    public ToDo(String task, boolean isCompleted) {
        super(task, isCompleted);
    }

    @Override
    public String getTaskType() {
        return "Todo";
    }

    @Override
    public String getStatus() {
        return String.format("%s", isCompleted());
    }

    @Override
    public String getDescription() {
        return getTask();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
