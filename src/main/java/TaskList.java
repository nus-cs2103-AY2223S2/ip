import java.util.LinkedList;
import java.util.List;

public class TaskList {
    private List<Task> taskList = new LinkedList<>();

    public int getListSize() {
        return taskList.size();
    }

    public void addTask(String taskName) {
        Task newTask = new Task(taskName);
        taskList.add(newTask);
    }

    public void delTask(int taskIndex) {
        this.taskList.remove(taskIndex - 1);
    }

    public void markTask(int taskIndex) {
        Task taskToMark = this.taskList.get(taskIndex - 1);
        taskToMark.mark();
    }

    public void unmarkTask(int taskIndex) {
        Task taskToMark = this.taskList.get(taskIndex - 1);
        taskToMark.unmark();
    }

    public String getTaskList() {
        StringBuilder sb = new StringBuilder();
        int taskIndex;
        String taskName;

        for (int i = 0; i < taskList.size(); i++) {
            taskIndex = i + 1;
            taskName = taskList.get(i).toString();
            sb.append(String.format("%d. %s\n", taskIndex, taskName));
        }
        return sb.toString();
    }
}
