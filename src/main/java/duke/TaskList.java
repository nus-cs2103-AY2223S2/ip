package duke;

import java.util.ArrayList;
import java.util.Scanner;

import duke.exception.DukeException;

import duke.task.Task;

/** Contains task list */
public class TaskList {

    /** ArrayList with initial capacity of 100 to store tasks */
    private ArrayList<Task> tasks = new ArrayList<>(100);

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
     * Adds a task to the task list.
     *
     * @param task Task to add.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes the task at the given index.
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
        this.tasks.remove(index);
        return task;
    }

    /**
     * Marks the task at the given index.
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
     * Unmarks the test at the given index.
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
     * Processes a String representing a group of tasks
     * and adds these tasks to the list.
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
     * Converts the task list to a String
     * for storage.
     *
     * @return String representing tasks in task list.
     */
    public String createTaskListString() {
        StringBuffer representation = new StringBuffer();
        for (Task t : tasks) {
            representation.append(t.getFileRepresentation() + "\n");
        }
        return representation.toString();
    }

    public int getSize() {
        return this.tasks.size();
    }

    @Override
    public String toString() {
        StringBuffer res = new StringBuffer();
        for (int i = 0; i < this.getSize() - 1; i++) {
            res.append((i + 1) + ". " + this.getTaskAtIndex(i) + "\n");
        }
        res.append(this.getSize() + ". " + this.getTaskAtIndex(this.getSize() - 1));
        return res.toString();
    }

    private Task getTaskAtIndex(int index) {
        return this.tasks.get(index);
    }

    /**
     * Finds all tasks in the list that contains the given
     * keyword and returns a new TaskList containing only
     * these tasks.
     *
     * @param keyword The given keyword that tasks must contain.
     * @return New TaskList containing only matching tasks.
     */
    public TaskList findTasksByKeyword(String keyword) {
        TaskList filteredTaskList = new TaskList();
        for (Task t : this.tasks) {
            if (t.nameContainsKeyword(keyword)) {
                filteredTaskList.addTask(t);
            }
        }
        return filteredTaskList;
    }

}
