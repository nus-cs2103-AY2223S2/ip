package duke.task;

import java.io.IOException;
import java.util.ArrayList;

import duke.storage.Storage;
import duke.UI.UI;
public class TaskList {
    private static ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public Task getTask(int taskNumber) {
        return tasks.get(taskNumber);
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public int numberOfTasks() {
        return tasks.size();
    }

    public void listTask(UI ui) {

    }

    public void removeTask(int taskNumber) {
        tasks.remove(taskNumber);
    }

    public void mark(int taskNumber, Storage storage) {
        Task nameOfTask = tasks.get(taskNumber - 1);
        nameOfTask.markAsDone();
        System.out.println("Nice work! This task has been marked as done: \n" + nameOfTask);

        try {
            storage.save(this);
        } catch (IOException error) {
            System.err.println(error.getMessage());
        }
    }

    public void unmark(int taskNumber, Storage storage) {
        Task nameOfTask = tasks.get(taskNumber - 1);
        nameOfTask.unmarkAsDone();
        System.out.println("Noted. This task has been marked as not done yet: \n"  + nameOfTask);

        try {
            storage.save(this);
        } catch (IOException error) {
            System.err.println(error.getMessage());
        }
    }

    public void addTask(Task taskToAdd, Storage storage) {
        tasks.add(taskToAdd);
    }
}
