package duke;

import java.util.ArrayList;

/**
 * List to contain the tasks in Duke
 */
public class TaskList {
    private ArrayList<Task> list;

    /**
     * Constructor of the TaskList
     * @param size Size of the list
     */
    public TaskList(int size) {
        list = new ArrayList<Task>(size);
    }
    public TaskList() {
        list = new ArrayList<Task>();
    }

    /**
     * Adds the task to the end of the list
     * @param task Task to be added
     */
    public void addTask(Task task) {list.add(task);
    }

    /**
     * Gets the size of the list
     * @return Size of the list
     */
    public int getSize() {
        return list.size();
    }

    /**
     * Gets the task at the given index
     * @param index Index of the task needed
     * @return Task at the index given
     */
    public Task getTask(int index) {
        assert 1 <= index + 1 && index + 1 <= getSize() :
                "('o')!! :: OOPS!!! Index should be a positive integer at most the number of tasks.";
        return list.get(index);
    }

    /**
     * Removes the task at the given index
     * @param index Index of the task to be removed
     * @return Task that was removed
     */
    public Task removeTask(int index) {
        assert 1 <= index + 1 && index + 1 <= getSize() :
                "('o')!! :: OOPS!!! Index should be a positive integer at most the number of tasks.";
        return list.remove(index);
    }

    public TaskList findTask(String str) {
        assert !str.equals("") :
                "('o')!! :: OOPS!!! Keyword cannot be empty.";
        TaskList result = new TaskList();
        for (int i = 0; i < this.getSize(); i++) {
            if (this.getTask(i).getName().contains(str)) {
                result.addTask(this.getTask(i));
            }
        }
        return result;
    }
}
