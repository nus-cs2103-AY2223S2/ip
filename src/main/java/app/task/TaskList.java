package app.task;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Represents the List of Tasks, and the primary class that the
 * app interacts with. A TaskList allows for modification of the Tasks contained
 * within, including deletion and insertion, getting a Task at a specified index,
 * and iterating through each contained Task.
 *
 * Modification of Tasks contained in the TaskList can only be done through the
 * TaskList.
 */
public class TaskList implements Iterable<Task> {
    private final List<Task> tasks;

    /**
     * Initialises an empty TaskList
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public Task getTask(int listIndex) {
        return this.tasks.get(listIndex);
    }

    public List<Task> getAllTasks() {
        return this.tasks;
    }

    public int size() {
        return this.tasks.size();
    }

    /**
     * Adds a task to the TaskList. Requires a task type as specified by TaskTypes,
     * and the correct mappings of the task. Throws exceptions for if the inputs are invalid.
     * <br>
     * Tasks are always added to the end of the TaskList.
     * @param type one of the available task types provided in TaskTypes.
     * @param args map of argument names to their values.
     * @return the same Task object that was added into the TaskList
     * @throws InvalidDateTimeException
     * @throws InvalidInputException
     */
    public Task addTask(TaskTypes.Type type, Map<String, String> args)
            throws InvalidDateTimeException, InvalidInputException {
        Task newTask;
        String desc = args.get("Description");
        switch (type) {
        case TODO:
            newTask = new ToDo(desc);
            break;
        case DEADLINE:
            String by = args.get("by");
            newTask = new Deadline(desc, by);
            break;
        case EVENT:
            String from = args.get("from");
            String to = args.get("to");
            newTask = new Event(desc, from, to);
            break;
        default:
            throw new InvalidInputException("Task not recognised");
        }
        this.tasks.add(this.tasks.size(), newTask); // always adds to the end
        return newTask;
    }


    /**
     * Adds a task following addTask. Additionally, marks a task done after adding.
     * @param type one of the available task types provided in TaskTypes.
     * @param args map of argument names to their values.
     * @return the same Task object that was added into the TaskList
     * @throws InvalidDateTimeException
     * @throws InvalidInputException
     */
    public Task addDoneTask(TaskTypes.Type type, Map<String, String> args)
            throws InvalidDateTimeException, InvalidInputException {
        Task newTask = addTask(type, args); // add to end of list
        int index = this.tasks.size() - 1; // mark the last/latest item as done
        markAsDone(index);
        assert newTask.isDone();
        return newTask;
    }

    /**
     * Pops (deletes and returns) a task from the TaskList, given the index of the task.
     * Note that the index is starts from zero - Commands accept user input which starts from 1.
     * @param index of the Task - starts from zero.
     * @return the deleted Task.
     * @throws InvalidInputException
     */
    public Task deleteTask(int index) throws InvalidInputException {
        if (this.tasks.isEmpty()) {
            throw new InvalidInputException("Hello hello there is no task to delete!!");
        }

        try {
            Task deletedTask = this.tasks.get(index);
            this.tasks.remove(index);
            return deletedTask;

        } catch (IndexOutOfBoundsException e) {
            throw new InvalidInputException("This task doesn't exist in your list.");
        }
    }

    /**
     * Marks a task as done at the given index.
     * Note that the index is starts from zero - Commands accept user input which starts from 1.
     * @param index of the Task - starts from zero.
     * @return returns true if task is already marked done, false if not.
     * @throws IndexOutOfBoundsException
     */
    public boolean markAsDone(int index) throws IndexOutOfBoundsException {
        boolean isAlreadyMarked = false;
        Task task = this.tasks.get(index);
        if (task.isDone()) {
            isAlreadyMarked = true;
        }
        task.markAsDone();
        return isAlreadyMarked;
    }

    /**
     * Marks a task as UNdone at the given index.
     * Note that the index is starts from zero - Commands accept user input which starts from 1.
     * @param index of the Task - starts from zero.
     * @return returns true if task is already marked UNdone, false if not.
     * @throws IndexOutOfBoundsException
     */
    public boolean unmarkDone(int index) throws IndexOutOfBoundsException {
        boolean isAlreadyMarked = false;
        Task task = this.tasks.get(index);
        if (!task.isDone()) {
            isAlreadyMarked = true;
        }
        task.unmarkDone();
        return isAlreadyMarked;
    }

    @Override
    public Iterator<Task> iterator() {
        return this.tasks.iterator();
    }
}
