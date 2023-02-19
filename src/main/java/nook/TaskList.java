package nook;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import tasks.Task;
import tasks.TaskComparator;

/**
 * Represents a list of tasks. It contains methods for adding, retrieving and deleting tasks.
 */
public class TaskList {
    private List<Task> list;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Constructs a TaskList from an initial list of tasks.
     *
     * @param initList The initial list of tasks to use for the TaskList.
     */
    public TaskList(List<Task> initList) {
        this.list = initList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            int count = i + 1;
            String res = count + "." + getTask(i).toString();
            if (i != getSize() - 1) {
                res += "\n ";
            }
            sb.append(res);
        }
        return sb.toString();
    }

    /**
     * Retrieves a task from the TaskList at the specified index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return list.get(index);
    }


    /**
     * Adds a task to the TaskList.
     *
     * @param task The task to add.
     */
    public void addTask(Task task) {
        list.add(task);
    }

    /**
     * Deletes a task from the TaskList at the specified index.
     *
     * @param index The index of the task to delete.
     */
    public void deleteTask(int index) {
        list.remove(index);
    }

    /**
     * Gets the number of tasks in the TaskList.
     *
     * @return The number of tasks in the TaskList.
     */
    public int getSize() {
        return list.size();
    }


    public List<Task> getTaskList() {
        Collections.sort(list, new TaskComparator());
        return list;
    }

    /**
     * Iterates through the existing TaskList in order to find tasks with descriptions
     * that match the specified keyword, storing them in a new TaskList and then returning
     * the TaskList containing the results of the search
     *
     * @param keyword the keyword of the task description to be searched in the tasklist
     */
    public TaskList search(String keyword) {
        List<Task> resultList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Task currTask = list.get(i);
            if (currTask.toString().contains(keyword)) {
                resultList.add(currTask);
            }
        }
        return new TaskList(resultList);
    }
}
