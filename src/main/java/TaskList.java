import java.util.ArrayList;

public class TaskList {

    private final ArrayList<Task> tasks;

    TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    String generateTaskDetails() {
        String str = "";
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            str += t.getDetailsToSave();
            if (i != tasks.size() - 1) {
                str += '\n';
            }
        }
        return str;
    }

    Task get(int num) {
        return tasks.get(num);
    }

    int size() {
        return tasks.size();
    }

    Task mark(int num) throws DukeException {
        Task t = tasks.get(num);
        if (t.isDone()) {
            throw new DukeInvalidArgumentException("Huh? You've already done this task!");
        } else {
            t.mark();
        }
        return t;
    }

    Task unmark(int num) throws DukeException {
        Task t = tasks.get(num);
        if (!t.isDone()) {
            throw new DukeInvalidArgumentException("Huh? You haven't even done this task!");
        } else {
            t.unmark();
        }
        return t;
    }

    void add(Task task) {
        tasks.add(task);
    }

    Task delete(int num) {
        Task t = tasks.get(num - 1);
        tasks.remove(num - 1);
        return t;
    }

    boolean isEmpty() {
        return tasks.isEmpty();
    }
}
