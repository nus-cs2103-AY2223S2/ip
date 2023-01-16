import java.util.Objects;
import java.util.ArrayList;


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
}
