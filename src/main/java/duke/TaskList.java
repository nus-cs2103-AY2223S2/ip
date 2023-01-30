package duke;

import java.util.ArrayList;
import java.util.Locale;

public class TaskList {
    private ArrayList<Task> tasks;

    TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    TaskList() {
        this.tasks = new ArrayList<>();
    }

    ArrayList<Task> getList() {
        return this.tasks;
    }

    Task getTask(int taskNumber){
        return tasks.get(taskNumber - 1);
    }

    int numberOfTasks() {
        return tasks.size();
    }

    void markTaskAsDone(int taskNumber) throws DukeException {
        if (taskNumber < 1 || taskNumber > numberOfTasks()) {
            throw new DukeException("OOPS!!! Invalid task number. Please try again.");
        }
        tasks.get(taskNumber - 1).markAsDone();
    }

    void markTaskAsIncomplete(int taskNumber) throws DukeException {
        if (taskNumber < 1 || taskNumber > numberOfTasks()) {
            throw new DukeException("OOPS!!! Invalid task number. Please try again.");
        }
        tasks.get(taskNumber - 1).markAsNotDone();
    }

    void add(Task t) {
        tasks.add(t);
    }

    public void deleteTaskFromList(int taskNumber) throws DukeException {
        if (taskNumber < 1 || taskNumber > numberOfTasks()) {
            throw new DukeException("OOPS!!! Invalid task number. Please try again.");
        }
        tasks.remove(taskNumber - 1);
    }



}
