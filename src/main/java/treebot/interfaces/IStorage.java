package treebot.interfaces;

import treebot.tasks.Task;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public interface IStorage {

    ArrayList<Task> loadTasks() throws FileNotFoundException;

    void saveTasks(ArrayList<Task> taskListState) throws IOException;


}
