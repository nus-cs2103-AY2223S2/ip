import java.util.ArrayList;

/**
 * The TaskList class represent all the tasks added by the user.
 *
 * @author Chia Jeremy
 */

public class TaskList {

    private final Storage storage = new Storage("data/tasks.txt");
    private final ArrayList<Task> taskList = new ArrayList<>(100);
    private final Feedback fb;

    public TaskList(ArrayList<String[]> tasks) {
        System.out.println("tasks len: " + tasks.size());
        for (int i = 0; i < tasks.size(); i++) {
            Task task;
            if (tasks.get(i)[0].equals("T")) {
                task = new Todo(tasks.get(i)[2]);
            } else if (tasks.get(i)[0].equals("D")) {
                task = new Deadline(tasks.get(i)[2], tasks.get(i)[3]);
            } else {
                task = new Event(tasks.get(i)[2], tasks.get(i)[3], tasks.get(i)[4]);
            }
            if (tasks.get(i)[1].equals("X")) {
                task.markDone();
            }
            taskList.add(task);
        }
        this.fb = new Feedback();
    }

    public void addTask(Task task) {
        this.taskList.add(task);
        this.storage.add(task);
        this.fb.addedTask(task, this.taskList.size());
    }

    public void deleteTask(int index) {
        Task task = this.taskList.get(index);
        this.taskList.remove(task);
        this.storage.delete(index);
        this.fb.deletedTask(task, this.taskList.size());
    }

    public void markTask(int index) {
        this.taskList.get(index).markDone();
        this.storage.mark(index);
        this.fb.markedTask(this.taskList.get(index));
    }

    public void unmarkTask(int index) {
        this.taskList.get(index).unmarkDone();
        this.storage.unmark(index);
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
