package duke;

import java.util.ArrayList;

import duke.task.Task;

public class TaskList {
    private ArrayList<Task> tasksList;

    TaskList(ArrayList<Task> tasksList) throws DukeException {
        this.tasksList = tasksList;
    }

    TaskList() {
        this.tasksList = new ArrayList<Task>(100);
    }

    public Task get(int index) {
        return tasksList.get(index);
    }

    public ArrayList<Task> getList() {
        return tasksList;
    }

    public int size() {
        return tasksList.size();
    }

    public void add(Task newTask) {
        this.tasksList.add(newTask);
    }

    public void remove(int taskNo) {
        this.tasksList.remove(taskNo);
    }

    public void clear() {
        this.tasksList.clear();
    }
}
