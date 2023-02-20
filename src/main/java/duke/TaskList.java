package duke;

import duke.tasks.Task;

import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> data = new ArrayList<>();

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
    public void mark(int pos) { this.data.get(pos).mark(); }
    public void unmark(int pos) {
        this.data.get(pos).unmark();
    }
    public int getSize() { return this.data.size();}

    public ArrayList<Task> findEntry(String key) {
        ArrayList<Task> matches = new ArrayList<>();
        for (int i = 0; i < this.getSize(); i++) {
            if (this.data.get(i).getDescription().contains(key)) {
                matches.add(this.data.get(i));
            }
        }
        return matches;
    }

    public String printData() {
        String tasks = "";
        for (int i = 0; i < this.data.size(); i++) {
            tasks += (i+1) + ". " + this.data.get(i).toString() + "\n";
        }
        return tasks;
    }
}
