public class TaskList {
    // taskList is 1 indexed
    private Task[] taskList;
    private int taskCount;

    public TaskList() {
        this.taskList = new Task[100];
        this.taskCount = 0;
    }

    public Task getTask(int taskNumber) throws TaskNotFoundException {
        if (taskNumber - 1 >= taskCount) {
            throw new TaskNotFoundException();
        }
        return taskList[taskNumber - 1];
    }

    public int getLength() {
        return taskCount;
    }

    public void addTask(Task task) {
        this.taskList[taskCount] = task;
        this.taskCount++;
    }

    public void setDone(int taskNumber, boolean done) throws TaskNotFoundException {
        if (taskNumber - 1 >= taskCount) {
            throw new TaskNotFoundException();
        }
        taskList[taskNumber - 1].setDone(done);
    }

    @Override
    public String toString() {
        if (taskCount == 0) {
            return "No tasks found.";
        }
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
