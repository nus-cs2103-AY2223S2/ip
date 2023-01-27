package duke;

import java.util.ArrayList;
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> taskList) {
        this.tasks = taskList;
    }

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public Task getTask(int taskNumber) throws DukeException {
        try {
            Task chosenTask = tasks.get(taskNumber - 1);
            return chosenTask;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Task does not exist!");
        }
    }

    public Task deleteTask(Task chosen) throws DukeException {
        this.tasks.remove(chosen);
        return chosen;
    }

    public Task markTask(Task chosen) throws DukeException {
        chosen.mark();
        return chosen;
    }

    public Task unmarkTask(Task chosen) throws DukeException {
        chosen.unmark();
        return chosen;
    }

    public ArrayList<Task> getMatchingTasks(String keyword) {
        ArrayList<Task> selected = new ArrayList<>();
        for (Task task: tasks) {
            if (task.toString().contains(keyword)) {
                selected.add(task);
            }
        }
        return selected;
    }


    public ArrayList<Task> getTaskList() {
        return tasks;
    }

    public int size() {
        return tasks.size();
    }
}
