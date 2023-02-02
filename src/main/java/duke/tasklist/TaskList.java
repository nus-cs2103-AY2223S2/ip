package duke.tasklist;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import duke.duke_exception.DukeException;
import duke.tasklist.task_types.Task;

public class TaskList implements Serializable {
    private static DukeException invalidIndex = new DukeException("Invalid index keyed.");
    private ArrayList<Task> list;

    public TaskList() {
        list = new ArrayList<>();
    }

    public TaskList(List<Task> list) {
        this.list = new ArrayList<Task>(list);
    }

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
        return String.format("Now you have %d tasks in the list\n", list.size());
    }

    public int getSize() {
        return list.size();
    }

    public TaskList filter(String name) {
        Stream<Task> taskStream = this.list.stream();
        return new TaskList(taskStream.filter(task -> task.getName().contains(name))
                .collect(Collectors.toList()));
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < list.size(); i++) {
            int index = i + 1;
            str += "\n" + index + ". " + list.get(i).toString();
        }

        return str + "\n";
    }
}
