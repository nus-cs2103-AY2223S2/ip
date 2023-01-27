package entities;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import exceptions.DukeFileNotFoundException;
import exceptions.InvalidInputException;
import storage.Storage;
import Ui.Ui;

public class TaskList {
    protected Storage storage;
    ArrayList<Task> list = new ArrayList<>();

    public TaskList(Storage storage) throws InvalidInputException, FileNotFoundException {
        this.storage = storage;
        if (!storage.load(this)) {
            throw new InvalidInputException("Sorry! I have detected weird inputs in the commands");
        }
    }

    public void addTask(Task t) {
        this.list.add(t);
    }

    public int getSize() {
        return this.list.size();
    }

    public Task getTask(int index) {
        return this.list.get(index);
    }

    public void deleteTask(int index) {
        this.list.remove(index);
    }


}
