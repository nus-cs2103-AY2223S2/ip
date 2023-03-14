package treebot.utils;

import treebot.interfaces.IStorage;
import treebot.tasks.Task;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class StorageStub implements IStorage {
    @Override
    public ArrayList<Task> loadTasks() throws FileNotFoundException {
        return null;
    }

    @Override
    public void saveTasks(ArrayList<Task> taskListState) throws IOException {

    }
}
