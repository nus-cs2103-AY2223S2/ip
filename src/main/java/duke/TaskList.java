package duke;

import java.util.ArrayList;
import java.util.List;
/** Represent the list of Task objects */
public class TaskList {
    private List<Task> taskList = new ArrayList<>();

    public int getListSize() {
        return taskList.size();
    }

    public Task getTask(int taskIndex) {
        return this.taskList.get(taskIndex);
    }

    public void addTask(Task newTask) {
        taskList.add(newTask);
    }

    public void delTask(int taskIndex) {
        this.taskList.remove(taskIndex - 1);
    }

    public void markTask(int taskIndex) {
        assert taskList.size() > 0;
        Task taskToMark = this.getTask(taskIndex);
        taskToMark.mark();
    }

    public void unmarkTask(int taskIndex) {
        assert taskList.size() > 0;
        Task taskToMark = this.getTask(taskIndex);
        taskToMark.unmark();
    }

    /**
     * Get all the Task objects and display them to user.
     * @return String representation of list of Task objects.
     */
    public String getTaskList() {
        StringBuilder sb = new StringBuilder();
        int taskIndex;
        String taskName;
        for (int i = 0; i < taskList.size(); i++) {
            taskIndex = i + 1;
            taskName = taskList.get(i).toString();
            System.out.println(taskName);
            sb.append(String.format("%d. %s\n", taskIndex, taskName));
        }
        return sb.toString();
    }

    /**
     * Returns the formatted list of tasks containing the keyword.
     * @param keyword The keyword given by the user.
     * @return String of list of tasks matching keyword.
     */
    public String findTasks(String keyword) {
        StringBuilder sb = new StringBuilder();
        int taskIndex;
        String taskName;

        for (int i = 0; i < taskList.size(); i++) {
            taskIndex = i + 1;
            taskName = taskList.get(i).toString();
            if (taskName.contains(keyword)) {
                sb.append(String.format("%d. %s\n", taskIndex, taskName));
            }
        }
        return sb.toString();
    }
}
