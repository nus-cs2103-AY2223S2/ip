package duke;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.task.Task;

public class TaskList {
    private ArrayList<Task> taskStore = new ArrayList<>(100);

    public TaskList() {

    }

    public TaskList(String taskList) {
        this.processTaskListString(taskList);
    }

    public void addTask(Task task) {
        taskStore.add(task);
    }

    public Task deleteTask(int index) throws DukeException {
        if (index >= this.getSize()) {
            throw new DukeException("There aren't that many tasks in the list!");
        } else if (index < 0) {
            throw new DukeException("I don't recognise that task number.");
        }
        Task task = this.getTaskAtIndex(index);
        this.taskStore.remove(index);
        return task;
    }

    public Task markTaskDone(int index) throws DukeException {
        if (index >= this.getSize()) {
            throw new DukeException("There aren't that many tasks in the list!");
        } else if (index < 0) {
            throw new DukeException("I don't recognise that task number.");
        }
        this.getTaskAtIndex(index).setDone(true);
        return this.getTaskAtIndex(index);
    }

    public Task unmarkTask(int index) throws DukeException {
        if (index >= this.getSize()) {
            throw new DukeException("There aren't that many tasks in the list!");
        } else if (index < 0) {
            throw new DukeException("I don't recognise that task number.");
        }
        this.getTaskAtIndex(index).setDone(false);
        return this.getTaskAtIndex(index);
    }

    private void processTaskListString(String taskList) {
        Scanner sc = new Scanner(taskList);
        while (sc.hasNextLine()) {
            String taskStringRepresentation = sc.nextLine();
            this.addTask(Task.createTaskFromStringRepresentation(taskStringRepresentation));
        }
        sc.close();
    }

    public String createTaskListString() {
        StringBuffer representation = new StringBuffer();
        for (Task t : taskStore) {
            representation.append(t.getFileRepresentation() + "\n");
        }
        return representation.toString();
    }

    public int getSize() {
        return this.taskStore.size();
    }

    @Override
    public String toString() {
        StringBuffer res = new StringBuffer();
        for (int i = 0; i < this.getSize() - 1; i++) {
            res.append((i + 1) + ". " + this.getTaskAtIndex(i) + "\n");
        }
        if (this.getSize() - 1 >= 0) {
            res.append(this.getSize() + ". " + this.getTaskAtIndex(this.getSize() - 1));
        }
        return res.toString();
    }

    private Task getTaskAtIndex(int index) {
        return this.taskStore.get(index);
    }

    public TaskList findTasksByKeyword(String keyword) {
        TaskList filteredTaskList = new TaskList();
        for (Task t : this.taskStore) {
            if (t.nameContainsKeyword(keyword)) {
                filteredTaskList.addTask(t);
            }
        }
        return filteredTaskList;
    }

}
