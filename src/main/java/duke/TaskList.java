package duke;
import java.util.ArrayList;
class TaskList {
    private ArrayList<Task> tasks;
    TaskList() {
        tasks = Save.loadSave();
    }
    public ArrayList<Task> get() {
        return tasks;
    }
    public Task mark(boolean isMark, int index) throws IndexOutOfBoundsException{
        Task task = tasks.get(index);
        task.mark(isMark);
        Save.makeSave(tasks);
        return task;
    }
    public void add(Task task) {
        tasks.add(task);
        Save.makeSave(tasks);
    }
    public Task delete(int index) throws IndexOutOfBoundsException {
        Task task = tasks.remove(index);
        Save.makeSave(tasks);
        return task;
    }
}