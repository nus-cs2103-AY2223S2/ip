package duke.task;
import duke.exceptions.NeroException;

import java.util.ArrayList;

/**
 * TaskList stores all current tasks
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Constructor for TaskList, uses an ArrayList containing tasks
     */
    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }


    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * @param index location of the task in the TaskList
     * @return Task at the index of the TaskList
     */
    public Task get(int index) {
        return taskList.get(index);
    }

    /**
     * Removes task from taskList
     * @param index location of the task to be removed
     * @throws NeroException Throws when index does not exist
     */
    public void removeTask(int index) throws NeroException {
        try {
            taskList.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new NeroException("Please enter a valid index from 0 to "
                    + (taskList.size() - 1));
        }
    }
    public void set(int index, Task newTask) throws NeroException {
        try {
            taskList.set(index, newTask);
        } catch (IndexOutOfBoundsException e) {
            throw new NeroException("Please enter a valid index from 0 to "
                    + (taskList.size() - 1));
        }
    }


    public TaskList findMatchingTasks(String word) {
        TaskList newTaskList = new TaskList();
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).containsWord(word)) {
                newTaskList.addTask(taskList.get(i));
            }
        }
        return newTaskList;
    }

    /**
     * Prints the toString of all tasks in the taskList
     */
    public String printTasks() {
        String toPrintTasks = "";
        for (int i = 0; i < taskList.size(); i++) {
            toPrintTasks += String.format("%d. %s", (i + 1), taskList.get(i).toString()) + "\n";
        }
        return toPrintTasks;
    }

    public int getSize() {
        return taskList.size();
    }
}
