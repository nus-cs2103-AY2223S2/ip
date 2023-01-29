package duke.tasks;

import java.util.List;
import java.util.stream.Collectors;

public class TaskList {

    private List<Task> taskList;

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }

    public int getSize() {
        return taskList.size();
    }

    public void addTask(Task newTask) {
        taskList.add(newTask);
    }

    public Task removeTask(int index) {
        return taskList.remove(index);
    }

    public Task markTask(int index) {
        Task task = taskList.get(index);
        task.mark();
        return task;
    }
    public Task unmarkTask(int index) {
        Task task = taskList.get(index);
        task.unmark();
        return task;
    }

    public List<Task> findTask(String s) {
        return taskList.stream()
                .filter(task -> task.getTask().contains(s))
                .collect(Collectors.toList());
    }


}
