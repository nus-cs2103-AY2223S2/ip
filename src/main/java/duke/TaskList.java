package duke;

import java.util.ArrayList;

import duke.task.Task;
import duke.enums.Views;

public class TaskList {
    private ArrayList<Task> tasksList;

    TaskList(ArrayList<Task> tasksList) throws DukeException {
        this.tasksList = tasksList;
    }

    TaskList() {
        this.tasksList = new ArrayList<Task>(100);
    }

    public Task get(int index) throws DukeException {
        try {
            return tasksList.get(index);
        } catch (Exception e) {
            throw new DukeException(Views.OUT_RANGE_ERR_STRING.eng());
        }
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
