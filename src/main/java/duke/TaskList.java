package duke;
import duke.Exceptions.NeroException;

import java.util.ArrayList;

/**
 * TaskList stores all current tasks
 * @param <Task>
 */
public class TaskList<Task> {
    private ArrayList<Task> taskList;

    /**
     * Constructor for TaskList, uses an ArrayList containing tasks
     */
    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }


    void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * @param index location of the task in the TaskList
     * @return Task at the index of the TaskList
     */
    Task get(int index) {
        return taskList.get(index);
    }

    /**
     * Removes task from taskList
     * @param index location of the task to be removed
     * @throws NeroException Throws when index does not exist
     */
    void removeTask(int index) throws NeroException {
        try {
            taskList.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new NeroException("Please enter a valid index from 0 to "
                    + (taskList.size() - 1));
        }
    }
    void set(int index, Task newTask) throws NeroException {
        try {
            taskList.set(index, newTask);
        } catch (IndexOutOfBoundsException e) {
            throw new NeroException("Please enter a valid index from 0 to "
                    + (taskList.size() - 1));
        }
    }

    /**
     * Prints the toString of all tasks in the taskList
     */
    String printTasks() {
        String toPrintTasks = "";
        for (int i = 0; i < taskList.size(); i++) {
            toPrintTasks += taskList.get(i).toString() + "\n";
        }
        return toPrintTasks;
    }

    int getSize() {
        return taskList.size();
    }
}
