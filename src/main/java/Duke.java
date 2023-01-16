import java.util.Objects;
import java.util.ArrayList;
import java.util.Iterator;


public class Duke {
    protected final ArrayList<Task> tasks = new ArrayList<>();
    protected final String name;

    public Duke() {
        this.name = "Duke";
    }

    public void print_structured_string(String s) {
        String longLine = "____________________________________________________________";
        System.out.println(longLine + "\n" + s + "\n" + longLine);
    }

    public String addMsg(Task task) {
        return "added: " + task;
    }

    public String listTasksMsg() {
        String s = "";
        int count = 1;
        for (Task t: this.tasks) {
            s += count + ". " + t;
            if (count < tasks.size()) {
                s += "\n";
            }
            count += 1;
        }
        return s;
    }

    public String greeting() {
        return String.format("Hello! I'm %s \nWhat can I do for you?", this.name);
    }

    public String echo(String s) {
        return s;
    }

    public String endMsg() {
        return "Bye. Hope to see you again soon!";
    }

    public boolean isEnd(String s) {
        return Objects.equals(s.toLowerCase(), "bye");
    }

    public void addTask(Task t) {
        this.tasks.add(t);
    }

    public void markTaskDone(String s) {
        for (Iterator<Task> iterator = this.tasks.iterator(); iterator.hasNext(); ) {
            Task t = iterator.next();
            if (t.getValue().equals(s)) {
                t.markDone();
            }
        }
    }

    public String markTaskDone(int idx) {
        Task t = this.tasks.get(idx);
        t.markDone();
        return String.format("Nice! I've marked this task as done:\n  %s", t);
    }

    public void unmarkTaskDone(String s) {
        for (Iterator<Task> iterator = this.tasks.iterator(); iterator.hasNext(); ) {
            Task t = iterator.next();
            if (t.getValue().equals(s)) {
                t.unmarkDone();
            }
        }
    }

    public String unmarkTaskDone(int idx) {
        Task t = this.tasks.get(idx);
        t.unmarkDone();
        return String.format("OK, I've marked this task as not done yet:\n  %s", t);
    }

    public void removeTask(String s) {
        this.tasks.removeIf(task -> task.getValue() == s);
    }
}
