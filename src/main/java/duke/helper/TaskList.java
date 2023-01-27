package duke.helper;

import duke.task.Task;

import java.io.IOException;
import java.util.ArrayList;

/**
 * TaskList class that handles all the tasks
 */
public class TaskList {
    private final ArrayList<Task> tasks;
    private Ui Ui;

    public TaskList(ArrayList<Task> tasks) throws IOException {
        this.tasks = tasks;
        this.Ui = new Ui();
    }

    /**
     * Adds the task to the task list
     *
     * @param task task to be added
     */
    public void addToTasks(Task task) {
        tasks.add(task);
    }

    /**
     * Prints all the tasks stored in the list
     */
    public void outputList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
    }

    /**
     * Deletes the task from the tasklist
     *
     * @param taskNo Task number to be deleted
     * @return task that has been deleted
     */
    public Task deleteTask(int taskNo) {
        Task task = tasks.get(taskNo);
        tasks.remove(taskNo);
        Ui.showDelete(task, tasks.size());
        return task;
    }

    public void mark(boolean isDone, String taskId) {
        int taskNo = Integer.parseInt(taskId) - 1;
        Task taskToMark = tasks.get(taskNo);
        taskToMark.setIsDone(isDone);
        Ui.showMark(isDone, taskToMark);
    }

    public void handleTaskOutput() {
        Task task = tasks.get(tasks.size() - 1);
        Ui.showTaskOutput(task, tasks.size());
    }

    /**
     * Outputs the tasklist stored
     *
     * @return tasklist stored
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
