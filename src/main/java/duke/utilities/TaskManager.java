package duke.utilities;

import duke.exceptions.DukeException;
import duke.tasks.ITask;

import java.util.ArrayList;

public class TaskManager {
    private ArrayList<ITask> _tasks;
    private final Storage _storage;


    public TaskManager(String filePath) {
        _tasks = new ArrayList<>();
        _storage = new Storage(filePath);
    }
    public int getObjectIndex(Object obj){
        return _tasks.indexOf(obj);
    }

    public void load() throws DukeException {
        _tasks = _storage.load();
    }

    public ArrayList<ITask> getTasks() {
        return _tasks;
    }

    public Object[] find(String keyword) {
        return _tasks.stream().filter(x -> x.descriptionContain(keyword)).toArray();
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
