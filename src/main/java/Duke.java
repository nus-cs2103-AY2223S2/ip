import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    protected final ArrayList<Task> tasks;

    protected Duke() {
        this.tasks = new ArrayList<>();
    }

    // methods
    public void addTask(Task task_given) {
        this.tasks.add(task_given);
    }

    public String msg_of_add(Task task) {
        return "add: " + task.name;
    }

    public String print_curr_tasks() {
        String res = "";
        if (tasks.size() == 0) {
            res = "Sorry this list is empty T^T";
            return res;
        }
        for (int i = 0; i < tasks.size(); i++) {
            if (i == tasks.size() - 1) {
                res += i + ": " + tasks.get(i);
            } else {
                res += i + ": " + tasks.get(i) + "\n";
            }
        }
        return res;
    }

    public String echo(String input) {
        return input;
    }

    public String ending() {
        return "Bye~ Hope to see you again soon:)";
    }

    public String greeting() {
        return "Hi~ I'm Duke>_< \nWhat can I do for you?";
    }

    public String separate(String str) {
        String sep_line = "---------------------------------------------------------------";
        return sep_line + "\n" + str + "\n" + sep_line;
    }
}
