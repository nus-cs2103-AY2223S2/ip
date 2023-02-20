package duke.duke;

import duke.tasks.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> data = new ArrayList<>();

    public void addEntry(Task task) {
        this.data.add(task);
        System.out.println("added: " + task.getDescription());
    }

    public void addFileEntry(Task task) {
        this.data.add(task);
    }

    public Task getEntry(int pos) {
        return this.data.get(pos);
    }
    public void removeEntry(int pos) { this.data.remove(pos);}
    public void markDone(int pos) { this.data.get(pos).markDone(); }
    public void unmarkDone(int pos) {
        this.data.get(pos).unmarkDone();
    }
    public int getSize() { return this.data.size();}

    @Override
    public String toString() {
        String tasks = "";
        for (int i = 0; i < this.data.size(); i++) {
            tasks = (i+1) + ". " + this.data.get(i).toString() + "\n";
        }
        return tasks;
    }
}
