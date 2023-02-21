package duke.tasklist;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import duke.duke_exception.DukeException;
import duke.tasklist.task_types.Task;
import duke.utility.enums.UpdateType;

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
     * @return a reply message string.
     */
    public String addTask(Task task) {
        tasks.add(task);
        String message = "Added one task!\n" + task.toString() + "\n" + this.getTotal();
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
     * @return a reply message string.
     * @throws DukeException if the index is out of bounds.
     */
    public String deleteTask(int index) throws DukeException {
        try {
            String message = "Deleted one task \n" + this.getTask(index).toString() + "\n";
            tasks.remove(index);
            message += this.getTotal();
            return message;
        } catch (IndexOutOfBoundsException e) {
            throw invalidIndex;
        }

    }


    /**
     * Marks a task from the ArrayList based on the given index.
     * 
     * @param index the index of the ArrayList.
     * @return a reply message string.
     * @throws DukeException if the index is out of bounds.
     */
    public String markedTask(int index) throws DukeException {
        try {
            tasks.get(index).setMarked(true);
            String message = "Nice! One Task Down!\n" + this.getTask(index).toString() + "\n";
            return message;
        } catch (IndexOutOfBoundsException e) {
            throw invalidIndex;
        }
    }


    /**
     * Unmarks a task from the ArrayList based on the given index.
     * 
     * @param index the index of the ArrayList.
     * @return a reply message string.
     * @throws DukeException if the index is out of bounds.
     */
    public String unmarkedTask(int index) throws DukeException {
        try {
            tasks.get(index).setMarked(false);
            String message = "One more task to go ):\n" + this.getTask(index).toString() + "\n";
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

    /**
     * Filters for tasks based on the given keyword.
     * 
     * @param name the keyword to search in every task.
     * @return a reply message string.
     */
    public String filter(String name) {
        Stream<Task> taskStream = this.tasks.stream();
        TaskList filteredTasks = new TaskList(taskStream.filter(task -> task.getName().contains(name))
                .collect(Collectors.toList()));
        String message = filteredTasks.getSize() == 0 ? "No results found." : "\nHere are the matching tasks in your list:" + "\n" + filteredTasks.toString();
        return message;
    }

    /**
     * Updates a task based on the specific index and type of update.
     * 
     * @param index the index of the array.
     * @param type the type of update.
     * @param newValue the updated value.
     * @return a reply message string.
     * @throws DukeException if the index is out of bounds.
     */
    public String update(int index, UpdateType type, String newValue) throws DukeException {
        try {
            String message = "";
            tasks.get(index).update(type, newValue);
            message += "Updated Task: \n" + this.getTask(index).toString() + "\n";
            return message;
        } catch (IndexOutOfBoundsException e) {
            throw invalidIndex;
        }
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
