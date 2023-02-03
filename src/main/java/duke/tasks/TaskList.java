package duke.tasks;

import java.util.ArrayList;

import duke.exceptions.DukeExceptions;
import duke.exceptions.InvalidIndexException;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {

    }
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task deleteTask(String input) throws DukeExceptions {
        int index = Integer.parseInt(input.substring(7)) - 1;
        if (index >= this.getSize()) {
            throw new InvalidIndexException();
        } else {
            Task thisTask = tasks.get(index);
            tasks.remove(index);
            return thisTask;
        }
    }

    public String printList() {
        StringBuilder sb = new StringBuilder("here's your list:\n");
        for (int i = 0; i < this.getSize(); i++) {
            sb.append(i + 1).append(".").append(tasks.get(i).toString()).append(System.lineSeparator());
        }
        return sb.toString();
    }

    public int getSize() {
        return tasks.size();
    }
}