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
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(List<Task> newTasks) {
        this.tasks = new ArrayList<Task>(newTasks);
    }


    /**
     * Adds a task into the ArrayList.
     * 
     * @param task the added task
     */
    public String addTask(Task task) {
        String message = "Added one task!\n" + task.toString() + "\n" + this.getTotal();
        tasks.add(task);
        return message;
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
            return tasks.get(index);
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
    public String deleteTask(int index) throws DukeException {
        try {
            String message = "Deleted one task \n" + this.getTask(index).toString() + "\n" + this.getTotal();
            tasks.remove(index);
            return message;
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
    public String markedTask(int index) throws DukeException {
        try {
            String message = "Nice! One Task Down!\n" + this.getTask(index).toString() + "\n";
            tasks.get(index).setMarked(true);
            return message;
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
    public String unmarkedTask(int index) throws DukeException {
        try {
            String message = "One more task to go ):\n" + this.getTask(index).toString() + "\n";
            tasks.get(index).setMarked(false);
            return message;
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
        return String.format("Now you have %d tasks in the list\n", tasks.size());
    }


    /**
     * Returns list size.
     * 
     * @return a int containing the size number of the list.
     */
    public int getSize() {
        return tasks.size();
    }

    public String filter(String name) {
        String message = "\nHere are the matching tasks in your list:" + "\n";
        Stream<Task> taskStream = this.tasks.stream();
        TaskList filteredTasks = new TaskList(taskStream.filter(task -> task.getName().contains(name))
                .collect(Collectors.toList()));
        message += filteredTasks.toString();
        return message;
    }


    /**
     * Returns a string of every element in the list
     * 
     * @return a String containing every task in the list.
     */
    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < tasks.size(); i++) {
            int index = i + 1;
            str += "\n" + index + ". " + tasks.get(i).toString();
        }

        return str + "\n";
    }
}
