package duke;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> array;

    public TaskList() {
        array = new ArrayList<>();
    }

    public void addTask(Task task) {
        array.add(task);
    }

    public void deleteTask(int index) {
        array.remove(index);
    }

    public void markTask(int index, boolean mark) {
        if (mark) {

        } else {

        }
    }

    public Task getTask(int index){
        if (index < array.size()) {
            return array.get(index);
        } else {
            return null;
        }
    }

    public int getLength() {
        return array.size();
    }
}
