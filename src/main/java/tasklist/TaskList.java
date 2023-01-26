package tasklist;

import java.io.Serializable;
import java.util.ArrayList;

import duke_exception.DukeException;
import tasklist.task_types.Task;

public class TaskList implements Serializable {
    private static DukeException invalidIndex = new DukeException("Invalid index keyed.");
    private ArrayList<Task> list = new ArrayList<>();

    public void addTask(Task task) {
        list.add(task);
    }

    public Task getTask(int index) throws DukeException {
        try {
            return list.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw invalidIndex;
        }
    }

    public void deleteTask(int index) throws DukeException {
        try {
            list.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw invalidIndex;
        }

    }

    public void markedTask(int index) throws DukeException {
        try {
            list.get(index).setStatus(true);
        } catch (IndexOutOfBoundsException e) {
            throw invalidIndex;
        }

    }

    public void unmarkedTask(int index) throws DukeException {
        try {
            list.get(index).setStatus(false);
        } catch (IndexOutOfBoundsException e) {
            throw invalidIndex;
        }
    }

    public String getTotal() {
        return String.format("Now you have %d tasks in the list", list.size());
    }

    public int getSize() {
        return list.size();
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < list.size(); i++) {
            int index = i + 1;
            System.out.println("\n" + index + ". " + list.get(i).toString());
        }

        return str;
    }
}
