package duke.tasklist;

import java.util.ArrayList;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.ui.Ui;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task, Storage storage, Ui ui) throws DukeException {
        this.tasks.add(task);
        storage.saveTasks(this.tasks);
        ui.printTask(task, this.tasks, true);
    }

    public void deleteTask(int taskNum, Storage storage, Ui ui) throws DukeException {
        int taskListIndex = taskNum - 1;
        Task taskToRemove = this.tasks.get(taskListIndex);
        this.tasks.remove(taskListIndex);
        storage.saveTasks(this.tasks);
        ui.printTask(taskToRemove, this.tasks, false);
    }

    public void markTask(int taskNum, Storage storage, Ui ui, boolean toMark) throws DukeException {
        int taskListIndex = taskNum - 1;
        if (!this.tasks.get(taskListIndex).getMark().equals(toMark ? 'X' : ' ')) {
            this.tasks.get(taskListIndex).toggleMark();
            storage.saveTasks(this.tasks);
        }
        ui.printMarkTask(this.tasks.get(taskListIndex), toMark);
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    public int size() {
        return this.tasks.size();
    }
}
