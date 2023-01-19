import java.util.ArrayList;

public class TaskList extends ArrayList<Task> {

    public TaskList() {
        super();
    }

    public void addTask(Task task) {
        super.add(task);
        Utils.output("Got it. I've added this task:\n\t " + task + "\n\tNow you have " + size() + " tasks in the list.");
    }

    public void removeTask(Task task) {
        super.remove(task);
    }

    public void mark(int i) {
        super.get(i-1).mark();
        Utils.output("Nice! I've marked this task as done:\n\t  " + get(i));
    }

    public void unMark(int i) {
        super.get(i-1).unMark();
        Utils.output("OK, I've marked this task as not done yet:\n\t  " + get(i));
    }

    @Override
    public Task get(int i) {
        return super.get(i-1);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (Task task : this) {
            if (i == size()) {
                sb.append(i+".").append(task).append("\t");
            } else {
                sb.append(i + ".").append(task).append("\n\t");
                i++;
            }
        }
        return sb.toString();
    }
}
