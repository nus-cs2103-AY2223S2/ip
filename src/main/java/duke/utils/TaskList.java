package duke.utils;

import java.time.LocalDateTime;
import java.util.ArrayList;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskType;
import duke.tasks.ToDo;

public class TaskList {

    private ArrayList<Task> array;

    public TaskList() {
        this(new ArrayList<>());
    }

    public TaskList(ArrayList<Task> array) {
        this.array = array;
    }

    public ArrayList<Task> getArray() {
        return this.array;
    }

    public int size() {
        return this.array.size();
    }

    public Task getTask(int taskIndex) {
        return this.array.get(taskIndex);
    }

    public void addToList(String title, TaskType type, LocalDateTime start,
                          LocalDateTime end, boolean done, boolean shouldPrintOutput) {
        Task task;
        if (type == TaskType.TODO) {
            task = new ToDo(title, done);
        } else if (type == TaskType.DEADLINE) {
            task = new Deadline(title, end, done);
        } else if (type == TaskType.EVENT) {
            task = new Event(title, start, end, done);
        } else {
            Ui.println("Something seems wrong...");
            return;
        }
        this.array.add(task);
        if (shouldPrintOutput) {
            Ui.println("Added this to your task list:");
            Ui.println("  " + task.toString());
            Ui.println(String.format("Number of tasks left: %d", this.array.size()));
        }
    }

    public void deleteTask(int taskIndex) {
        Task deletedTask = this.array.remove(taskIndex);
        Ui.println("Removed this from your task list:");
        Ui.println("  " + deletedTask.toString());
        Ui.println(String.format("Number of tasks left: %d", this.array.size()));
    }

    public void changeTaskCompletionStatus(int taskNumber, boolean completionStatus) {
        Task task = this.array.get(taskNumber);
        task.setDone(completionStatus);
        if (completionStatus) {
            Ui.println("Solid work man! This task is marked done");
        } else {
            Ui.println("Aww what happened? This task is marked as undone");
        }
        String toPrint = task.toString();
        Ui.println(toPrint);
    }

}
