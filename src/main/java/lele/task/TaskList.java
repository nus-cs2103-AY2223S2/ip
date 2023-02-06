package lele.task;

import java.lang.StringBuilder;
import java.util.ArrayList;

/**
 * The place to store all the user's task, and perform
 * operations on.
 *
 */
public class TaskList {
    protected ArrayList<Task> list;

    /**
     * Loads the task list provided from storage.
     *
     * @param taskList Loaded with tasks from storage
     */
    public TaskList(ArrayList<Task> taskList) {
        this.list = taskList;
    }

    /**
     * If the user does not have existing date, a new task list is created.
     * Thus, this constructor will be used instead of the previous one.
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Prints the current list of tasks.
     * @return Output to user.
     */
    public String printList() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.list.size(); i++) {
            sb.append((i+1) + "." + this.list.get(i) + "\n");
        }
        return sb.toString();
    }

    /**
     * Finds the tasks where their description matches
     * the regex of the query.
     *
     * @param regex The description to find.
     * @return Output tasks to user.
     */
    public String findTasks(String regex) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.list.size(); i++) {
            Task task = this.list.get(i);
            if (this.list.get(i).getDescription().contains(regex)) {
                sb.append((i + 1) + "." + task + "\n");
            }
        }
        return sb.toString();
    }

    /**
     * The size of the task list, to be used by
     * other internal methods for calculations.
     *
     * @return The size of the current task list.
     */
    public int listSize() {
        return this.list.size();
    }

    /**
     * A specific task to be returned from a query.
     *
     * @param index The location of the task in the array list.
     * @return The task queried.
     */
    public Task getTask(int index) {
        return this.list.get(index);
    }

    /**
     * Returns the list of task.
     *
     * @return List of task.
     */
    public ArrayList<Task> getList() {
        return this.list;
    }

    /**
     * Marks the specific task specified by the query.
     *
     * @param index The location of the task in the array list
     */
    public void markStatus(int index) {
        assert list.get(index-1) instanceof Task : "Object called should be of type task.";
//        Use an exception instead of assert to handle the code below
//        assert list.contains(list.get(index-1)) : "The task list should contain the task being marked.";
        list.get(index - 1).markStatus(true);
    }

    /**
     * Unmarks the specific task specified by the query.
     *
     * @param index The location of the task in the array list.
     */
    public void unMarkStatus(int index) {
        list.get(index - 1).markStatus(false);
    }

    /**
     * Adds a task to the task list.
     *
     * @param task Task to be added.
     */
    public void addTasks(Task task) {
        this.list.add(task);
    }

    /**
     * Deletes a task to the task list.
     *
     * @param inputIndex The location of the task in the array list.
     */
    public Task deleteTasks(int inputIndex) {
//        Use an exception instead of assert to handle the code below
//        assert this.listSize() >= inputIndex : "Index parameter queried should be within the task size";
        Task task = this.list.get(inputIndex - 1);
        this.list.remove(inputIndex - 1);

        return task;
    }

}
