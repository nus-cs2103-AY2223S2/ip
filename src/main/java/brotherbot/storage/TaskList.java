package brotherbot.storage;

import brotherbot.ui.Ui;
import java.io.PrintWriter;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskStorage;

    public TaskList() {
        this.taskStorage =  new ArrayList<>();
    }

    public TaskList(String input) {
        // check validity

    }

    public Task get(int i) {
        return this.taskStorage.get(i);
    }

    public void add(Task task) {
        this.taskStorage.add(task);
    }

    public void remove(int i) {
        this.taskStorage.remove(i);
    }

    public void mark(int i) {
        taskStorage.get(i).markAsDone();
    }

    public int size() {
        return this.taskStorage.size();
    }

    public void display(Ui ui) {
        int i = 0;
        for(Task task: this.taskStorage) {
            ui.toUser((i + 1) + ". " + task.toString());
            i++;
        }

    }

    public void write(PrintWriter x) {
        int i = 0;
        for (Task task : this.taskStorage) {
            x.println((i + 1) + ". " + this.taskStorage.get(i));
            i++;
        }
    }

    public String getPrintFormat(int index) {
        return this.taskStorage.get(index).toString();
    }
}
