package duke.task;

import java.io.IOException;
import java.util.ArrayList;

import duke.storage.Storage;
import duke.ui.Ui;

public class TaskList {
    private static ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public int size() {
        return tasks.size();
    }

    public void remove(int index) {
        tasks.remove(index);
    }

    public void list(Ui ui) {
        ui.addToResponseMessage("Here are the tasks in your list:");
        int numOfTasks = tasks.size();
        for (int i = 1; i <= numOfTasks; i++) {
            ui.addToResponseMessage(i + "." + tasks.get(i - 1));
        }
    }

    public void mark(int taskNumber, Storage storage) {
        assert taskNumber >= 0 : "taskNumber is non-negative";
        Task task = tasks.get(taskNumber - 1);
        task.markAsDone();
        try {
            storage.writeTasksToFile(this);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void unmark(int taskNumber, Storage storage) {
        assert taskNumber >= 0 : "taskNumber is non-negative";
        Task task = tasks.get(taskNumber - 1);
        task.markAsNotDone();
        try {
            storage.writeTasksToFile(this);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public Task deleteTask(int taskNumber, Storage storage) {
        assert taskNumber >= 0 : "taskNumber is non-negative";
        Task task = tasks.remove(taskNumber - 1);
        try {
            storage.writeTasksToFile(this);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return task;
    }

    public void addTask(Task newTask, Storage storage) {
        tasks.add(newTask);
        try {
            storage.addTaskToFile(newTask);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void searchTask(String str, Ui ui) {
        assert str != "" : "str is supposed to be non-empty";
        ArrayList<Task> results = new ArrayList<>();
        ArrayList<Integer> indices = new ArrayList<>();
        int index = 1;
        for (Task task : tasks) {
            if (task.getDescription().contains(str)) {
                results.add(task);
                indices.add(index);
            }
            index++;
        }
        ui.addToResponseMessage("Here are the matching tasks in your list:");
        for (int i = 0; i < results.size(); i++) {
            ui.addToResponseMessage(indices.get(i) + ". " + results.get(i).toString());
        }
    }
}
