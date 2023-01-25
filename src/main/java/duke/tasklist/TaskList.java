package duke.tasklist;

import java.util.*;
import duke.ui.Ui;
import duke.tasktypes.Task;

public class TaskList {
    private ArrayList<Task> taskStorage;
    private int numTasks;
    private Ui ui;

    public TaskList(Ui ui) {
        taskStorage = new ArrayList<>();
        this.numTasks = 0;
        this.ui = ui;
    }

    public void loadTask(Task task) {
        this.taskStorage.add(task);
        numTasks++;
    }

    public void addTask(Task task) {
        this.taskStorage.add(task);
        numTasks++;
        ui.taskAdd(task, numTasks);
    }

    public void deleteTask(int toDelete) {
        Task deleted = taskStorage.remove(toDelete - 1);
        numTasks--;
        ui.taskDelete(deleted, numTasks);
    }

    public void markTask(int mark) {
        Task marked = taskStorage.get(mark - 1);
        marked.markDone();
        ui.markTaskDone(marked);
    }

    public void unmarkTask(int unmark) {
        Task unmarked = taskStorage.get(unmark - 1);
        unmarked.markUndone();
        ui.markTaskUndone(unmarked);
    }

    public void printTasks() {
        int count = 1;
        System.out.println("Here are the tasks in your list:");
        for (Task task : taskStorage) {
            String output = String.format("%d.%s", count++, task.toString());
            System.out.println(output);
        }
    }

    public ArrayList<Task> getTasks() {
        return taskStorage;
    }

    public int getNumTasks() {
        return numTasks;
    }

}
