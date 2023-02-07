package duke;

import tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public TaskList(List<Task> initList) {
        this.list = initList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            int count = i + 1;
            String res = count + "." + getTask(i).toString();
            if (i != getSize() - 1) {
                res += "\n\t ";
            }
            sb.append(res);
        }
        return sb.toString();
    }

    public Task getTask(int index) {
        return list.get(index);
    }

    public void addTask(Task task) {
        list.add(task);
    }

    public void deleteTask(int index) {
        list.remove(index);
    }

    public int getSize() {
        return list.size();
    }

    public List<Task> getTaskList() {
        return list;
    }

    public TaskList search(String keyword) {
        List<Task> resultList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Task currTask = list.get(i);
            if (currTask.toString().contains(keyword)) {
                resultList.add(currTask);
            }
        }
        return new TaskList(resultList);
    }
}
