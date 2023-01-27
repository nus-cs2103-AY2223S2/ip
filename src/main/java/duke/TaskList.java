package duke;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Constructor for TaskList object
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a given task to the end of the list of tasks.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    public int getSize() {
        return this.tasks.size();
    }
    
    public String toStringIndexes(ArrayList<Integer> arrayList) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < arrayList.size(); i++) {
            str.append(i + 1).append(".").append(this.tasks.get(arrayList.get(i)).toString()).append("\n");
        }

        return str.toString();
    }

    public ArrayList<Integer> findIndexesContaining(String str) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < this.tasks.size(); i++) {
            if (this.getTask(i).getDescription().contains(str)) {
                arrayList.add(i);
            }
        }

        return arrayList;
    }

    /**
     * Deletes a Task of the given index from the list of tasks.
     *
     * @param index 0-indexed index of task to be deleted.
     * @return Task deleted.
     */
    public Task deleteTask(int index) {
        return this.tasks.remove(index);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < this.tasks.size(); i++) {
            str.append(i + 1).append(".").append(this.tasks.get(i).toString()).append("\n");
        }

        return str.toString();
    }
}
