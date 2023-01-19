import java.util.ArrayList;

/**
 * The TaskList class represent all the tasks added by the user.
 *
 * @author Chia Jeremy
 */

public class TaskList {

    private final ArrayList<Task> taskList;
    private final Feedback fb;

    public TaskList(int size) {
        this.taskList = new ArrayList<>(size);
        this.fb = new Feedback();
    }

    public void addTask(Task task) {
        this.taskList.add(task);
        this.fb.addedTask(task, this.taskList.size());
    }

    public void deleteTask(int index) {
        this.taskList.remove(index);
        this.fb.deletedTask(this.taskList.get(index), this.taskList.size());
    }

    public void markTask(int index) {
        this.taskList.get(index).markDone();
        this.fb.markedTask(this.taskList.get(index));
    }

    public void unmarkTask(int index) {
        this.taskList.get(index).unmarkDone();
        this.fb.unmarkedTask(this.taskList.get(index));
    }

    public void list() {
        this.fb.listTask(this.taskList);
    }

    public void help() {
        this.fb.help();
    }

    public void invalid() {
        this.fb.invalid();
    }

    @Override
    public String toString() {
        return this.taskList.toString();
    }
}
