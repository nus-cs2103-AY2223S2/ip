import java.time.LocalDateTime;
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
        for (String[] strings : tasks) {
            Task task;
            if (strings[0].equals("T")) {
                task = new Todo(strings[2]);
            } else if (strings[0].equals("D")) {
                LocalDateTime dateTime = LocalDateTime.parse(strings[3].trim());
                task = new Deadline(strings[2], dateTime);
            } else {
                LocalDateTime startDT = LocalDateTime.parse(strings[3].trim());
                LocalDateTime endDT = LocalDateTime.parse(strings[4].trim());
                task = new Event(strings[2], startDT, endDT);
            }
            if (strings[1].equals("X")) {
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