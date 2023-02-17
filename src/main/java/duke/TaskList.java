package duke;

import java.util.ArrayList;
import java.util.Collections;

import duke.task.Task;

/**
 * TaskList contains the task list and handles operations related to the list.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructor for TaskList.
     * @param tasks current arraylist of tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    /**
     * Constructor for TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Gets current arraylist of tasks.
     * @return arraylist of tasks
     */
    public ArrayList<Task> getArrayList() {
        return this.tasks;
    }

    /**
     * Gets the size of current task list
     * @return size of task list
     */
    public int getSize() {
        return this.tasks.size();
    }

    public Task getTaskAtIndex(int index) throws DukeException {
        try {
            return tasks.get(index);
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
    }

    /**
     * Lists tasks in TaskList.
     */
    public String listTasks() {
        int numTasks = tasks.size();
        String output = "Here are the tasks in your list:";
        if (numTasks == 0) {
            output += "\nYou do not have any tasks for now!";
        } else {
            for (int i = 0; i < numTasks; i++) {
                output += String.format("\n%d. %s", i + 1, tasks.get(i));
            }
        }
        return output;
    }

    /**
     * Marks task as done.
     * @param taskIndex index of specified task
     */
    public void markTask(int taskIndex) throws DukeException {
        try {
            tasks.get(taskIndex).markAsDone();
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
    }

    /**
     * Unmarks task.
     * @param taskIndex index of specified task
     */
    public void unmarkTask(int taskIndex) throws DukeException {
        try {
            tasks.get(taskIndex).markUndone();
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
    }

    /**
     * Deletes task.
     * @param taskIndex index of specified task
     */
    public void deleteTask(int taskIndex) throws DukeException {
        tasks.remove(taskIndex);
    }

    /**
     * Adds task.
     * @param newTask task to be added
     */
    public void addTask(Task newTask) {
        tasks.add(newTask);
    }

    /**
     * Finds and returns tasks in task list that matches keyword.
     * @param keyword
     */
    public String findKeywordMatches(String keyword) {
        int numMatch = 0;
        String output = "Here are the matching tasks in your list:";
        for (int i = 0; i < tasks.size(); i++) {
            String taskDesc = tasks.get(i).getDesc();
            if (taskDesc.contains(keyword)) {
                numMatch++;
                output += String.format("\n%d. %s", numMatch, tasks.get(i));
            }
        }
        if (numMatch == 0) {
            output += "\nThere are no matches!!";
        }
        return output;
    }

    public void sortTaskList() {
        Collections.sort(this.tasks, new TaskComparator());
    }

}
