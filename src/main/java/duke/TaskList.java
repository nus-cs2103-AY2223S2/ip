package duke;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> taskList = new ArrayList<>();

    public int getListSize() {
        return taskList.size();
    }

    public Task getTask(int taskIndex) {
        /*
        System.out.println(taskIndex);
        System.out.println(this.taskList);
        */
        return this.taskList.get(taskIndex);
    }

    public void addTask(Task newTask) {
        taskList.add(newTask);
    }

    public void delTask(int taskIndex) {
        this.taskList.remove(taskIndex - 1);
    }

    public void markTask(int taskIndex) {
        Task taskToMark = this.getTask(taskIndex);
        taskToMark.mark();
    }

    public void unmarkTask(int taskIndex) {
        Task taskToMark = this.getTask(taskIndex);
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
