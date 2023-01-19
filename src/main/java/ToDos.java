public class ToDos extends Task {
    public ToDos(String taskInfo) {
        super(taskInfo);
    }
    @Override
    public String getTaskInfo() {
        return "[T]" + super.getTaskInfo();
    }
}
