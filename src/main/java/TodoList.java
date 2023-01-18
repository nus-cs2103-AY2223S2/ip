public class TodoList {
    // taskList is 1 indexed
    private String[] taskList;
    private int taskCount;

    public TodoList() {
        this.taskList = new String[100];
        this.taskCount = 0;
    }

    public void addTask(String taskDescription) {
        this.taskList[taskCount] = taskDescription;
        this.taskCount++;
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
