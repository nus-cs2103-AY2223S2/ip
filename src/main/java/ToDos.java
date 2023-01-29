public class ToDos extends Task {
    public ToDos(String taskInfo) {
        super(taskInfo);
    }
    @Override
    public String getTaskInfoStatus() {
        return "[T]" + super.getTaskInfoStatus();
    }

    @Override
    public String getTaskInfo() {
        return "[T]" + super.getTaskInfoStatus();
    }
}
