package CatBot.TaskList;

import CatBot.CatBotException;

import java.util.ArrayList;
import java.util.Iterator;

public class TaskList implements Iterable<Task>, Iterator<Task> {
    private final ArrayList<Task> tasks;

    private int iteratorIndex = 0;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task newTask) {
        tasks.add(newTask);
    }

    public void delete(int index) throws CatBotException {
        try {
            tasks.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new CatBotException("Incorrect task number.");
        }
    }

    public void mark(int index, boolean isDone) throws CatBotException {
        try {
            tasks.get(index).setDone(isDone);
        } catch (IndexOutOfBoundsException e) {
            throw new CatBotException("Incorrect task number.");
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
