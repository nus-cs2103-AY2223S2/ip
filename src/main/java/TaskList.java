import java.util.ArrayList;

public class TaskList extends ArrayList<Task> {

    public TaskList() {
        super();
    }

    public int countMarkedTasks() {
        int count = 0;
        for (int i = 0; i < this.size(); i++) {
            count += this.get(i).isDone() ? 1 : 0;
        }
        return count;
    }

    public String list() {
        if (this.size() == 0) {
            return "You have no tasks.";
        }

        String str = "You have the following tasks: ";
        str.join(this.size() < 10
                ? "(So few~ good going!)\n"
                : "(So many >:O)\n");

        return str + this.toString() + "You have " + this.size() + " tasks.";
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < this.size(); i++) {
            str += (i + 1) + ". " + this.get(i) + "\n";
        }
        return str;
    }
}
