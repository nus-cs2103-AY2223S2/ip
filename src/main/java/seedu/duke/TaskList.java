package seedu.duke;

import seedu.duke.tasks.*;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructor for TaskList
     *
     * @param tasks List of Task to be initialized
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructor for empty TaskList
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Retrieves the Task at the specified index (in 0-indexing)
     *
     * @param index of the Task in list shown
     * @return Task at the specified index (0-indexing)
     */
    public Task get(int index) {
        return this.tasks.get(index);
    }

    /**
     * @return Size of the TaskList
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Format Task to be saved as String in save file
     *
     * @param index Index of Task to be unmarked (in 0-indexing)
     * @return String of formatted Task
     */
    public String formatTask(int index) {
        return get(index).formatTask();
    }


    /**
     * Adds Task to the TaskList
     *
     * @param newTask Task to be added
     * @return Updated TaskList
     */
    public TaskList addTask(Task newTask) {
        ArrayList<Task> updatedTasks = this.tasks;
        updatedTasks.add(newTask);
        return new TaskList(updatedTasks);
    }

    /**
     * Deletes Task from the TaskList
     *
     * @param index Index of Task to be added (in 0-indexing)
     * @return Updated TaskList
     */
    public TaskList deleteTask(int index) {
        ArrayList<Task> updatedTasks = this.tasks;
        updatedTasks.remove(index);
        return new TaskList(updatedTasks);
    }

    /**
     * Marks Task from the TaskList
     *
     * @param index Index of Task to be marked (in 0-indexing)
     * @return Updated TaskList
     */
    public TaskList markTask(int index) throws DukeException {
        ArrayList<Task> updatedTasks = this.tasks;
        Task unmarkedTask = updatedTasks.get(index);
        Task markedTask = unmarkedTask.markTask();
        updatedTasks.set(index, markedTask);
        return new TaskList(updatedTasks);
    }

    /**
     * Unmarks Task from the TaskList
     *
     * @param index Index of Task to be unmarked (in 0-indexing)
     * @return Updated TaskList
     */
    public TaskList unmarkTask(int index) throws DukeException {
        ArrayList<Task> updatedTasks = this.tasks;
        Task markedTask = updatedTasks.get(index);
        Task unmarkedTask = markedTask.unmarkTask();
        updatedTasks.set(index, unmarkedTask);
        return new TaskList(updatedTasks);
    }

    /**
     * Finds all Tasks in TaskList that contains the String of keyword(s), including sub-words
     *
     * @param keywords keywords provided by the user
     * @return TaskList containing all Tasks with matching keywords
     */
    public TaskList findTask(String keywords) throws DukeException {
        TaskList matchingTasks = new TaskList();
        for (int index = 0; index < getSize(); index++) {
            Task task = get(index);
            if (task.toString().contains(keywords)) {
                matchingTasks.addTask(task);
            }
        }
        if (matchingTasks.getSize() == 0) {
            throw new DukeException("There are no matching tasks!");
        } else {
            return matchingTasks;
        }
    }

    /**
     * Checks whether contents in two TaskLists are similar
     *
     * @param o Object to be compared with TaskList for equality
     * @return true if TaskLists have similar content, or else false
     */
    @Override
    public boolean equals (Object o) {
        if (o instanceof TaskList) {
            TaskList taskList = (TaskList) o;
            return taskList.tasks.equals(this.tasks);
        }
        return false;
    }
}
