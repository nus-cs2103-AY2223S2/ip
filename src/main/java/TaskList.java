public class TaskList {
    // taskList is 1 indexed
    private Task[] taskList;
    private int taskCount;

    public TaskList() {
        this.taskList = new Task[100];
        this.taskCount = 0;
    }

    public Task getTask(int taskNumber) {
        return taskList[taskNumber - 1];
    }

    public int getLength() {
        return taskCount;
    }

    public void addTask(Task task) {
        this.taskList[taskCount] = task;
        this.taskCount++;
    }

    public void setDone(int taskNumber, boolean done) {
        taskList[taskNumber - 1].setDone(done);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < taskCount; i++) {
            result.append(i + 1).append(".").append(taskList[i]).append("\n");
        }
        if (result.length() > 0) {
            result.deleteCharAt(result.length() - 1);
        }
        return result.toString();
    }
}
