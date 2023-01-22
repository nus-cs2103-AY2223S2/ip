package utilities;

import exceptions.DukeException;
import tasks.ITask;

import java.util.ArrayList;

public class TaskManager {
    private final ArrayList<ITask> _tasks;
    private Storage _storage;

    public TaskManager() {
        _tasks = new ArrayList<>();
    }

    public TaskManager(String filePath) throws DukeException {

        _storage = new Storage(filePath);
        _tasks = _storage.load();
    }

    public ArrayList<ITask> getTasks() {
        return _tasks;
    }

    public int size() {
        return _tasks.size();
    }

    public ITask remove(int index) throws DukeException {
        ITask task = _tasks.remove(index);
        _storage.saveAll(_tasks);
        return task;
    }

    public void add(ITask task) throws DukeException {
        _tasks.add(task);
        _storage.saveAll(_tasks);
    }

    public ITask mark(int index, boolean isMark) throws DukeException {
        ITask task = _tasks.get(index);
        if (isMark) {
            task.markAsDone();
        } else {
            task.markAsUnDone();
        }
        _storage.saveAll(_tasks);

        return task;
    }

}
