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
        return "Got it. I've added this task:\n " + task.toString() + "\nNow you have " +
                tasks.size() + " tasks in the list:D";
    }

    public String print_curr_tasks() {
        String res = "";
        if (tasks.size() == 0) {
            res = "Sorry this list is empty T^T";
            return res;
        }
        res += "Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            if (i == tasks.size() - 1) {
                res += (i+1) + ". " + tasks.get(i);
            } else {
                res += (i+1) + ". " + tasks.get(i) + "\n";
            }
        }
        return res;
    }

    public String mark_as_done(int index) {
        Task task = this.tasks.get(index);
        task.mark();
        return "Nice! I've marked this task as done:\n" + task.toString();
    }

    public String mark_as_undone(int index) {
        Task task = this.tasks.get(index);
        task.unmark();
        return "OK, I've marked this task as not done yet:\n" + task.toString();
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
