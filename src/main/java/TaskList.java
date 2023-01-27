import java.util.ArrayList;

public class TaskList extends ArrayList<Task> {

    public TaskList() {
        super();
    }

    public int countMarkedTasks() {
        int count = 0;
        for (Task task : this) {
            count += task.isDone() ? 1 : 0;
        }
        return count;
    }

    public String list() {
        if (this.size() == 0) {
            return "You have no tasks.";
        }

        String str = this.size() < 10
                ? "(So few~ good going!)\n"
                : "(So many >:O)\n";
        str = String.join(" ", "You have the following tasks:", str);

        return str + this + "You have " + this.size() + " tasks.";
    }

    public String storageFormat() {
        String str = "";
        for (Task task : this) {
            str = str + task.storageFormat();
        }
        return str;
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < this.size(); i++) {
            str = String.join("",str, String.valueOf(i + 1), ". ", this.get(i).toString(), "\n");
        }
        return str;
    }
}
