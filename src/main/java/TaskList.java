import java.time.LocalDateTime;
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

    public String dateFilter(LocalDateTime startDate, LocalDateTime endDate) {
        StringBuilder str = new StringBuilder();
        int count = 0;
        for (Task task : this) {
            if (task instanceof Deadline) {
                Deadline d = (Deadline) task;
                LocalDateTime t = d.getDeadline();
                if (t.isBefore(endDate)
                        && t.isAfter(startDate)) {
                    count++;
                    str.append(count).append(". ").append(d).append("\n");
                }
            } else if (task instanceof Event) {
                Event e = (Event) task;
                LocalDateTime f = e.getFrom();
                LocalDateTime t = e.getTo();
                if (t.isBefore(endDate)
                        && f.isAfter(startDate)) {
                    count++;
                    str.append(count).append(". ").append(e).append("\n");
                }

            }
        }
        return str + "You have " + count + " tasks in this period.";
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
        StringBuilder str = new StringBuilder();
        for (Task task : this) {
            str.append(task.storageFormat());
        }
        return str.toString();
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
