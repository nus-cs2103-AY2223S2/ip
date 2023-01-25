package duke;

import java.util.ArrayList;
import java.util.Collection;

public class TaskList {
    final static BadCommandException INDEX_OOB_ERROR =
            new BadCommandException("Index given is out of bounds!");
    protected ArrayList<Task> tasks;
    public TaskList() {
        tasks = new ArrayList<>();
    }
    public TaskList(Collection<Task> initTasks) {
        tasks = new ArrayList<>(initTasks);
    }

    private boolean isIndexInRange(int idx) {
        return idx < tasks.size() && idx >= 0;
    }
    public void addTask(Task newTask) {
        tasks.add(newTask);
    }
    public Task removeTask(int idx) throws BadCommandException {
        if (!isIndexInRange(idx)) {
            throw INDEX_OOB_ERROR;
        }
        Task deletedTask = tasks.get(idx);
        tasks.remove(idx);
        return deletedTask;
    }
    public void markTaskAsDone(int idx) throws BadCommandException {
        if (!isIndexInRange(idx)) {
            throw INDEX_OOB_ERROR;
        }
        tasks.get(idx).markAsDone();
    }

    public void unmarkTaskAsDone(int idx) throws BadCommandException {
        if (!isIndexInRange(idx)) {
            throw INDEX_OOB_ERROR;
        }
        tasks.get(idx).unmarkAsDone();
    }

    public Task getTask(int idx) throws BadCommandException {
        if (!isIndexInRange(idx)) {
            throw INDEX_OOB_ERROR;
        }
        return tasks.get(idx);
    }

    public TaskList getTasksByKeyword(String keyword) {
        keyword = keyword.trim().toLowerCase();
        TaskList matchingTasks = new TaskList();
        for (Task task: tasks) {
            if (task.toString().toLowerCase().contains(keyword)) {
                matchingTasks.addTask(task);
            }
        }
        return matchingTasks;
    }

    public int getSize() {
        return tasks.size();
    }
    @Override
    public String toString() {
        StringBuilder listOutput = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            listOutput.append(String.format(
                    "\t%d. %s",
                    i + 1,
                    tasks.get(i)
            ));
            if (i < tasks.size() - 1) {
                listOutput.append("\n");
            }
        }
        return listOutput.toString();
    }
}
