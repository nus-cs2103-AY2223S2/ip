import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public Task mark(int idx) {
        this.tasks.get(idx).mark();
        return this.tasks.get(idx);
    }

    public Task unmark(int idx) {
        this.tasks.get(idx).unmark();
        return this.tasks.get(idx);
    }

    public Task delete(int idx) {
        Task cur = this.tasks.get(idx);
        this.tasks.remove(idx);
        return cur;
    }

    public Task add(Task task) {
        this.tasks.add(task);
        return task;
    }

    public Task get(int idx) {
        return this.tasks.get(idx);
    }

    public int size() {
        return this.tasks.size();
    }

    @Override
    public String toString() {
        String msg = "";
        for(int i = 0; i < this.tasks.size(); i++) {
            msg += (i + 1) + ". ";
            msg += this.tasks.get(i);
            msg += "\n";
        }
        return msg;
    }
}
