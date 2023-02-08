package duke.task;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * A list of Tasks.
 *
 * @author Guo-KeCheng
 */
public class TaskList implements Iterable<Task> {

    private final ArrayList<Task> taskList;

    /**
     * TaskList class constructor.
     * Instantiates taskList to be an empty ArrayList.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * TaskList class constructor
     *
     * @param taskList Instantiate taskList with given arraylist
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Get the size of taskList
     *
     * @return Size of taskList
     */
    public int size() {
        return this.taskList.size();
    }

    /**
     * Get the task at a specific index of taskList
     *
     * @return Task at a specific index of taskList
     */
    public Task get(int index) {
        return this.taskList.get(index);
    }

    /**
     * Add task to taskList
     *
     * @param task Task to be added
     */
    public void add(Task task) {
        this.taskList.add(task);
    }

    /**
     * Remove task at a specific index of taskList
     *
     * @param index Index of taskList to be removed
     */
    public void remove(int index) {
        this.taskList.remove(index);
    }

    public boolean isEmpty() {
        return this.taskList.isEmpty();
    }


    @Override
    public Iterator<Task> iterator() {
        Iterator<Task> it = new Iterator<Task>() {

            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < size() && get(currentIndex) != null;
            }

            @Override
            public Task next() {
                return get(currentIndex++);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
        return it;
    }
}
