package seedu.duke;

import seedu.duke.Tasks.*;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> taskList;

    /**
     * Constructor for TaskList
     * @param taskList List of Task to be initialized
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Constructor for empty TaskList
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Retrieves the Task at the specified index (in 0-indexing)
     * @param index of the Task in list shown
     * @return Task at the specified index (0-indexing)
     */
    public Task get(int index) {
        return this.taskList.get(index - 1);
    }

    /**
     * Adds Task to the TaskList
     * @param newTask Task to be added
     * @return Updated TaskList
     */
    public TaskList addTask(Task newTask) {
        ArrayList<Task> updatedList = this.taskList;
        updatedList.add(newTask);
        return new TaskList(updatedList);
    }

    /**
     * Deletes Task from the TaskList
     * @param index Index of Task to be added (in 0-indexing)
     * @return Updated TaskList
     */
    public TaskList deleteTask(int index) {
        ArrayList<Task> updatedList = this.taskList;
        updatedList.remove(index);
        return new TaskList(updatedList);
    }

    /**
     * Marks Task from the TaskList
     * @param index Index of Task to be marked (in 0-indexing)
     * @return Updated TaskList
     */
    public TaskList markTask(int index) throws DukeException {
        ArrayList<Task> updatedList = this.taskList;
        Task unmarkedTask = updatedList.get(index);
        Task markedTask = unmarkedTask.markTask();
        updatedList.set(index, markedTask);
        return new TaskList(updatedList);
    }

    /**
     * Unmarks Task from the TaskList
     * @param index Index of Task to be unmarked (in 0-indexing)
     * @return Updated TaskList
     */
    public TaskList unmarkTask(int index) throws DukeException {
        ArrayList<Task> updatedList = this.taskList;
        Task markedTask = updatedList.get(index);
        Task unmarkedTask = markedTask.unmarkTask();
        updatedList.set(index, unmarkedTask);
        return new TaskList(updatedList);
    }

    /**
     * Format Task to be saved as String in save file
     * @param index Index of Task to be unmarked (in 0-indexing)
     * @return String of formatted Task
     */
    public String formatTask(int index) {
        return get(index).formatTask();
    }

    /**
     * @return Size of the TaskList
     */
    public int getSize() {
        return this.taskList.size();
    }

    /**
     * Checks whether contents in two TaskLists are similar
     * @param o Object to be compared with TaskList for equality
     * @return true if TaskLists have similar content, or else false
     */
    @Override
    public boolean equals (Object o) {
        if (o instanceof TaskList) {
            TaskList taskList = (TaskList) o;
            if(taskList.taskList.equals(this.taskList)) {
                return true;
            }
            return true;
        }
        return  false;
    }
}
