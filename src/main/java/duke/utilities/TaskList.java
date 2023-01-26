package duke.utilities;

import duke.tasks.DeadlineTask;
import duke.tasks.Task;
import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {

    private final ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        tasks = taskList;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task deleteTask(int index) {
        return
                tasks.remove(index);
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public Task markTaskAsDone(int index) {
        Task task = tasks.get(index);
        task.markDone();
        return task;
    }

    public Task unmarkTaskAsDone(int index) {
        Task task = tasks.get(index);
        task.unmarkDone();
        return task;
    }

    public ArrayList<Task> getAllTasksThatAreDueOn(LocalDate dueOnDate) {
        ArrayList<Task> arrayList = new ArrayList<>();

        for (Task task : tasks) {
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
        return tasks.size();
    }

    public boolean isEmpty() {
        return tasks.size() == 0;
    }

    public String toDukeFileString() {
        ArrayList<String> output = new ArrayList<>();

        for (Task task : tasks) {
            output.add(task.toDukeFileString() + "\n");
        }

        return String.join("", output);
    }
}
