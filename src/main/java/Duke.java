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

    public String listTasksMsg() {
        String s = "Here are the tasks in your list:\n";
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

    public String addTask(Task t) {
        this.tasks.add(t);
        return String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.", t, tasks.size());
    }

    public void markTaskDone(String s) {
        for (Iterator<Task> iterator = this.tasks.iterator(); iterator.hasNext(); ) {
            Task t = iterator.next();
            if (t.getName().equals(s)) {
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
            if (t.getName().equals(s)) {
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
        this.tasks.removeIf(task -> task.getName() == s);
    }

    public String deleteTask(int idx) {
        Task t = tasks.get(idx);
        tasks.remove(idx);
        return String.format("Noted. I've removed this task:\n  %s\nNow you have %d tasks in the list.", t, tasks.size());
    }
}
