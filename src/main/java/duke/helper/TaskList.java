package duke.helper;

import duke.task.Task;

import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    private Ui Ui;

    public TaskList(ArrayList<Task> tasks) throws IOException {
        this.tasks = tasks;
        this.Ui = new Ui();
    }

    public void addToTasks(Task task) {
        tasks.add(task);
    }

    public void outputList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
    }

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

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
