import java.time.LocalDateTime;
import java.util.ArrayList;

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
        // TODO: Edit such that it doesnt print anything
        Task task;
        if (type == TaskType.TODO) {
            task = new ToDo(title, done);
        } else if (type == TaskType.DEADLINE) {
            task = new Deadline(title, end, done);
        } else if (type == TaskType.EVENT) {
            task = new Event(title, start, end, done);
        } else {
            System.out.println("Something seems wrong...");
            return;
        }
        this.array.add(task);
        if (shouldPrintOutput) {
            System.out.println("Added this to your task list:");
            System.out.println("  " + task.toString());
            System.out.println(String.format("Number of tasks left: %d", this.array.size()));
        }
    }

    public void deleteTask(int taskIndex) {
        // TODO: Edit such that it doesnt print anything
        Task deletedTask = this.array.remove(taskIndex);
        System.out.println("Removed this from your task list:");
        System.out.println("  " + deletedTask.toString());
        System.out.println(String.format("Number of tasks left: %d", this.array.size()));
    }

    public void changeTaskCompletionStatus(int taskNumber, boolean completionStatus) {
        // TODO: Edit such that it doesnt print anything
        Task task = this.array.get(taskNumber);
        task.setDone(completionStatus);
        if (completionStatus) {
            System.out.println("Solid work man! This task is marked done");
        } else {
            System.out.println("Aww what happened? This task is marked as undone");
        }
        String toPrint = task.toString();
        System.out.println(toPrint);
    }

}
