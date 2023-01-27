package duke;
import duke.tasks.Task;
import java.io.Serializable;
import java.util.ArrayList;

public class TaskList implements Serializable {
    private ArrayList<Task> list;

    /**
     * TaskList constructor.
     */
    public TaskList() {
        this.list = new ArrayList<Task>();

    }

    /**
     * Returns the Task object at the specified index of the ArrayList.
     *
     * @param i An integer representing the index
     * @return Task
     */
    public Task get(int i) {
        return this.list.get(i);
    }

    /**
     * Adds a Task object into the ArrayList.
     *
     * @param t The Task object to be added into the ArrayList.
     */
    public void add(Task t) {
        this.list.add(t);
    }

    /**
     * Returns the size of the ArrayList.
     *
     * @return int representing the size.
     */
    public int size() {
        return this.list.size();
    }

    /**
     * Removes the Task object at the specified index of the ArrayList.
     *
     * @param i An integer representing the index
     */
    public void remove(int i) {
        this.list.remove(i);
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString() {
        return "" + this.list;
    }

}
