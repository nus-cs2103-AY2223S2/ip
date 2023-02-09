package catbot.tasklist;

import java.util.ArrayList;
import java.util.Iterator;

import catbot.CatBotException;

/**
 * Stores the task list and handles associated operations on it.
 */
public class TaskList implements Iterable<Task>, Iterator<Task> {
    private final ArrayList<Task> tasks;

    private int iteratorIndex = 0;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task newTask) {
        tasks.add(newTask);
    }

    /**
     * Deletes a task from the list.
     * @param index is the index of the task in the list.
     * @throws CatBotException if the index is out of bounds.
     */
    public void delete(int index) throws CatBotException {
        try {
            tasks.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new CatBotException("Invalid task number.");
        }
    }

    /**
     * Sets whether a task is done or not.
     * @param index is the index of the task in the list.
     * @param isDone is whether the task is done.
     * @throws CatBotException when the index is invalid.
     */
    public void mark(int index, boolean isDone) throws CatBotException {
        try {
            tasks.get(index).setDone(isDone);
        } catch (IndexOutOfBoundsException e) {
            throw new CatBotException("Invalid task number.");
        }
    }

    public int size() {
        return tasks.size();
    }

    @Override
    public boolean hasNext() {
        return iteratorIndex < tasks.size();
    }

    @Override
    public Task next() {
        return tasks.get(iteratorIndex++);
    }

    @Override
    public Iterator<Task> iterator() {
        iteratorIndex = 0;
        return this;
    }
}
