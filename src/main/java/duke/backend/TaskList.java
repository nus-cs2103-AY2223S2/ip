package duke.backend;

import duke.tasks.Task;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;


class TaskList {
    private final ArrayList<Task> tasks;
    private final Storage saveManager;

    public TaskList() throws IOException {
        //  load from file
        File prevTasks = new File("./tasks.txt");
        prevTasks.createNewFile();
        this.saveManager = new Storage(prevTasks);
        this.tasks = new ArrayList<>(saveManager.extractTasks());
    }

    public void add(Task t) {
        tasks.add(t);
    }


    public Task mark(int idx) {
        Task t = tasks.get(idx);
        t.mark();
        return t;
    }

    public Task unmarkIdx(int idx) {
        Task t = tasks.get(idx);
        t.unmark();
        return t;
    }
    public Task delete(int idx) {
        Task t = tasks.get(idx);
        tasks.remove(idx);
        return t;
    }

    public ArrayList<Task> getWholeList() {
        return this.tasks;
    }

    public void closeAndSave() {
        this.saveManager.saveTasks(this.tasks);
    }
}
