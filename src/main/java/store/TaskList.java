package store;

import dukeexception.DukeException;
import task.Task;
import userinteraction.Ui;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    public int getSize() {
        return tasks.size();
    }
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void listTask(String[] inputLine) throws DukeException {
        if (inputLine.length > 1) {
            throw new DukeException("Invalid format");
        }
        int number = 1;
        for (Task stored : tasks) {
            System.out.println(number + ". " + stored.toString());
            number++;
        }
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(String[] inputArr, Ui ui) throws DukeException {
        if (inputArr.length < 2) {
            throw new DukeException("Invalid format, please give numbers");
        }
        int index = Integer.parseInt(inputArr[1]) - 1;
        int size = this.getSize();
        if (index >= size | index < 0) {
            throw new DukeException("Index out of bounds");
        }
        Task task = tasks.get(index);
        ui.printDeleteTaskMsg(task, size - 1);
        tasks.remove(index);
    }

    public void markTask(boolean isMarked, String[] input, Ui ui) throws DukeException {
        if (input.length < 2) {
            throw new DukeException("Invalid format, please give numbers");
        }
        int index = Integer.parseInt(input[1]) - 1;
        int size = tasks.size();
        if (index >= size | index < 0) {
            throw new DukeException("Index out of bounds");
        }
        Task task = tasks.get(index);
        task.setChecked(isMarked);
        ui.printMarkTaskMsg(isMarked, task);
    }

}
