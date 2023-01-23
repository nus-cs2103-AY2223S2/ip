package twofive.data;

import twofive.exception.TaskDoneException;
import twofive.exception.TaskUndoneException;
import twofive.task.Task;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public String getTasksList() {
        StringBuilder stringBuilder = new StringBuilder("Here are the tasks in your list:\n");
        int taskIndex = 1;
        for (Task task: tasks) {
            stringBuilder.append(taskIndex + ". " + task);
            if (taskIndex - 1 < tasks.size() - 1) {
                stringBuilder.append("\n");
            }
            taskIndex++;
        }
        return stringBuilder.toString();
    }

    public int getTasksNum() {
        return tasks.size();
    }

    public Task deleteTask(int taskNum) {
        Task currentTask = tasks.get(taskNum);
        tasks.remove(currentTask);
        return currentTask;
    }

    public Task setTaskAsDone(int taskNum) throws TaskDoneException {
        Task currentTask = tasks.get(taskNum);
        currentTask.setDone();
        return currentTask;
    }

    public Task setTaskAsUndone(int taskNum) throws TaskUndoneException {
        Task currentTask = tasks.get(taskNum);
        currentTask.setUndone();
        return currentTask;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public String getTasksOnDateList(LocalDate date) {
        StringBuilder stringBuilder = new StringBuilder("Here are the tasks in your list due on "
                + date.format(DateTimeFormatter.ofPattern("EEE MMM d yyyy")) + ":\n");
        int taskIndex = 1;
        int numTasksDue = 0;
        for (Task task: tasks) {
            if (task.isToday(date)) {
                numTasksDue++;
            }
        }
        for (Task task: tasks) {
            if (task.isToday(date)) {
                stringBuilder.append(taskIndex + ". " + task);
                if (taskIndex - 1 < numTasksDue - 1) {
                    stringBuilder.append("\n");
                }
                taskIndex++;
            }
        }
        return stringBuilder.toString();
    }

    public String getSaveTasksString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Task task: tasks) {
            stringBuilder.append(task.getFileWriteString() + "\n");
        }
        return stringBuilder.toString();
    }
}
