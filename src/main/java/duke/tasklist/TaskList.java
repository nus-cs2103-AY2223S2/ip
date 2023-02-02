package duke.tasklist;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import duke.duke_exception.DukeException;
import duke.tasklist.task_types.Task;

/**
 * Represents a <code>TaskList</code> object that holds an ArrayList of Tasks together with CRUD
 * operations.
 * 
 * @author Brian Quek
 */
public class TaskList implements Serializable {
    private static DukeException invalidIndex = new DukeException("Invalid index keyed.");
    private ArrayList<Task> list;

    public TaskList() {
        list = new ArrayList<>();
    }

    public TaskList(List<Task> list) {
        this.list = new ArrayList<Task>(list);
    }


    /**
     * Adds a task into the ArrayList.
     * 
     * @param task the added task
     */
    public void addTask(Task task) {
        list.add(task);
    }


    /**
     * Returns a task from the ArrayList based on the given index.
     * 
     * @param index the index of the ArrayList.
     * @return a Task object based on the current index of the ArrayList
     * @throws DukeException if the index is out of bounds.
     */
    public Task getTask(int index) throws DukeException {
        try {
            return list.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw invalidIndex;
        }
    }


    /**
     * Deletes a task from the ArrayList based on the given index.
     * 
     * @param index the index of the ArrayList.
     * @throws DukeException if the index is out of bounds.
     */
    public void deleteTask(int index) throws DukeException {
        try {
            list.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw invalidIndex;
        }

    }


    /**
     * Marks a task from the ArrayList based on the given index.
     * 
     * @param index the index of the ArrayList.
     * @throws DukeException if the index is out of bounds.
     */
    public void markedTask(int index) throws DukeException {
        try {
            list.get(index).setStatus(true);
        } catch (IndexOutOfBoundsException e) {
            throw invalidIndex;
        }

    }


    /**
     * Unmarks a task from the ArrayList based on the given index.
     * 
     * @param index the index of the ArrayList.
     * @throws DukeException if the index is out of bounds.
     */
    public void unmarkedTask(int index) throws DukeException {
        try {
            list.get(index).setStatus(false);
        } catch (IndexOutOfBoundsException e) {
            throw invalidIndex;
        }
    }


    /**
     * Returns a String message showing how many tasks you have left in your list.
     * 
     * @return a String message showing the number of tasks you have left in your list.
     */
    public String getTotal() {
        return String.format("Now you have %d tasks in the list\n", list.size());
    }


    /**
     * Returns list size.
     * 
     * @return a int containing the size number of the list.
     */
    public int getSize() {
        return list.size();
    }

    public TaskList filter(String name) {
        Stream<Task> taskStream = this.list.stream();
        return new TaskList(taskStream.filter(task -> task.getName().contains(name))
                .collect(Collectors.toList()));
    }


    /**
     * Returns a string of every element in the list
     * 
     * @return a String containing every task in the list.
     */
    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < list.size(); i++) {
            int index = i + 1;
            str += "\n" + index + ". " + list.get(i).toString();
        }

        return str + "\n";
    }
}
