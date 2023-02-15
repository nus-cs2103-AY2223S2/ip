package Nerd.entities;

import java.util.ArrayList;
import java.io.FileNotFoundException;

import Nerd.exceptions.NerdException;
import Nerd.storage.Storage;

/**
 * Represents the list of Tasks of the Chat bot.
 */
public class TaskList {
    protected Storage storage;
    private ArrayList<Task> list;

    /**
     * Instantiates a TaskList Object that keeps track of the Tasks.
     *
     * @param storage The storage object that contains the preload data.
     */
    public TaskList(Storage storage) throws NerdException, FileNotFoundException {
        this.storage = storage;
        this.list = new ArrayList<>(100);
        if (!storage.load(this)) {
            throw new NerdException("Sorry! I have detected weird inputs in the commands");
        }
    }

    /**
     * Instantiates a TaskList Object that keeps track of the Tasks.
     */
    public TaskList() {
        this.list = new ArrayList<>(100);
    }

    public void addTask(Task t) {
        this.list.add(t);
    }

    public int getSize() {
        return this.list.size();
    }

    public Task getTask(int index) {
        assert index < 0 : "Invalid index";
        return this.list.get(index);
    }

    public void deleteTask(int index) {
        assert index < 0 : "Invalid index";
        this.list.remove(index);
    }

}
