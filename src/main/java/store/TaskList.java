package store;

import dukeexception.DukeException;
import task.Task;
import userinteraction.Ui;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> store;
    public TaskList(ArrayList<Task> store) {
        this.store = store;
    }
    public int getSize() {
        return store.size();
    }
    public ArrayList<Task> getStore() {
        return store;
    }

    public void listTask(String[] inputLine) throws DukeException {
        if (inputLine.length > 1) {
            throw new DukeException("Invalid format");
        }
        int number = 1;
        for (Task stored : store) {
            System.out.println(number + ". " + stored.toString());
            number++;
        }
    }

    public void addTask(Task task) {
        store.add(task);
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
        Task task = store.get(index);
        ui.printDeleteTaskMsg(task, size - 1);
        store.remove(index);
    }

    public void markTask(boolean isMarked, String[] input, Ui ui) throws DukeException {
        if (input.length < 2) {
            throw new DukeException("Invalid format, please give numbers");
        }
        int index = Integer.parseInt(input[1]) - 1;
        int size = store.size();
        if (index >= size | index < 0) {
            throw new DukeException("Index out of bounds");
        }
        Task task = store.get(index);
        task.setChecked(isMarked);
        ui.printMarkTaskMsg(isMarked, task);
    }

    public void findTask(String[] inputArr) throws DukeException {
        if (inputArr.length < 2) {
            throw new DukeException("Invalid format, please give search hint");
        }
        int number = 1;
        for (Task stored : store) {
            if (stored.getStr().equals(inputArr[1])) {
                System.out.println(number + ". " + stored.toString());
                number++;
            }
        }

    }

}
