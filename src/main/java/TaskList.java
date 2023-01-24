import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {

    private final ArrayList<Task> tasks;

    TaskList() {
        this.tasks = new ArrayList<>();
    }

    TaskList(ArrayList<Task> taskList) {
        this.tasks = taskList;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public Task deleteTask(int index) {
        return this.tasks.remove(index);
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    public Task markTaskAsDone(int index) {
        Task task = this.tasks.get(index);
        task.markDone();
        return task;
    }

    public Task unmarkTaskAsDone(int index) {
        Task task = this.tasks.get(index);
        task.unmarkDone();
        return task;
    }

    public ArrayList<Task> getAllTasksThatAreDueOn(LocalDate dueOnDate) {
        ArrayList<Task> arrayList = new ArrayList<>();

        for (Task task : this.tasks) {
            if (task instanceof DeadlineTask) {
                DeadlineTask deadlineTask = (DeadlineTask) task;

                if (deadlineTask.isDueOn(dueOnDate)) {
                    arrayList.add(deadlineTask);
                }
            }
        }

        return arrayList;
    }

    public int getSize() {
        return this.tasks.size();
    }

    public boolean isEmpty() {
        return this.tasks.size() == 0;
    }

    public void handleDueOnCommand(String[] tokens) throws DukeInvalidDueOnCommandException {

    }

    public String toDukeFileString() {
        ArrayList<String> output = new ArrayList<>();

        for (int i = 0; i < this.tasks.size(); i++) {
            Task task = this.tasks.get(i);
            output.add(task.toDukeFileString() + "\n");
        }

        return String.join("", output);
    }
}
