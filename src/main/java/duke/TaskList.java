package duke;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.task.Task;

/** Contains task list */
public class TaskList {
    /** ArrayList with initial capacity of 100 to store tasks */
    private ArrayList<Task> taskStore = new ArrayList<>(100);

    /**
     * Empty constructor.
     */
    public TaskList() {

    }

    /**
     * Constructs a TaskList from a String
     * representing all tasks to add to the list.
     *
     * @param taskList String representing all tasks
     *                 to add to the task list.
     */
    public TaskList(String taskList) {
        this.processTaskListString(taskList);
    }

    /**
     * Add a task to the task list.
     *
     * @param task Task to add.
     */
    public void addTask(Task task) {
        taskStore.add(task);
    }

    /**
     * Delete the task at the given index.
     *
     * @param index Index to delete task from.
     * @return Deleted task.
     * @throws DukeException if index is not valid.
     */
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

    /**
     * Mark the task at the given index.
     *
     * @param index Index to mark tast at.
     * @return Task that was marked.
     * @throws DukeException if index is not valid.
     */
    public Task markTaskDone(int index) throws DukeException {
        if (index >= this.getSize()) {
            throw new DukeException("There aren't that many tasks in the list!");
        } else if (index < 0) {
            throw new DukeException("I don't recognise that task number.");
        }
        this.getTaskAtIndex(index).setDone(true);
        return this.getTaskAtIndex(index);
    }

    /**
     * Unmark the test at the given index.
     *
     * @param index Index to unmark test at.
     * @return Task that was unmarked.
     * @throws DukeException if index is not valid.
     */
    public Task unmarkTask(int index) throws DukeException {
        if (index >= this.getSize()) {
            throw new DukeException("There aren't that many tasks in the list!");
        } else if (index < 0) {
            throw new DukeException("I don't recognise that task number.");
        }
        this.getTaskAtIndex(index).setDone(false);
        return this.getTaskAtIndex(index);
    }

    /**
     * Process a String representing a group of tasks
     * and add these tasks to the list.
     *
     * @param taskList String representing a group of tasks.
     */
    private void processTaskListString(String taskList) {
        Scanner sc = new Scanner(taskList);
        while (sc.hasNextLine()) {
            String taskStringRepresentation = sc.nextLine();
            this.addTask(Task.createTaskFromStringRepresentation(taskStringRepresentation));
        }
        sc.close();
    }

    /**
     * Convert the task list to a String
     * for storage.
     *
     * @return String representing tasks in task list.
     */
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

}
