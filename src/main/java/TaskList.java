import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public int getSize() {
        return tasks.size();
    }

    public Task getTask(int i) {
        return tasks.get(i);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void markTask(Task task) {
        task.setDone(true);
    }

    public void unmarkTask(Task task) {
        task.setDone(false);
    }

    public void deleteTask(int index) {
        tasks.remove(index);
    }

    public void showList(Ui ui) {
        ui.showList(this);
    }

    public void filterTasksByDate(Ui ui, LocalDate date) {
        int count = 0;
        for (Task task : tasks) {
            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                if (deadline.getDeadline().toLocalDate().isEqual(date)) {
                    ui.printUi(deadline.toString());
                    count++;
                }
            } else if (task instanceof Event) {
                Event event = (Event) task;
                if (event.getStartDateTime().toLocalDate().isEqual(date) ||
                        event.getEndDateTime().toLocalDate().isEqual(date) ||
                        (event.getStartDateTime().toLocalDate().isBefore(date) &&
                                event.getEndDateTime().toLocalDate().isAfter(date))) {
                    ui.printUi(event.toString());
                    count++;
                }
            }
        }
        ui.printUi("Number of tasks on " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ": " + count);
    }
}