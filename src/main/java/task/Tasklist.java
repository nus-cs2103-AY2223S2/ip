package task;

import duke.Storage;

import java.util.ArrayList;

public class Tasklist {
    private ArrayList<Task> list;
    private Storage backend;
    public Tasklist() {
        this.list = new ArrayList<>();
        this.backend = new Storage("./data/duke.txt");
    }

    public Tasklist(ArrayList<Task> list, Storage backend) {
        this.list = list;
        this.backend = backend;
    }

    public void add(Task task) {
        this.list.add(task);
        this.backend.save(this);
        System.out.println("Got it. I've added this task:\n " + task.toString());
    }
    public void markTaskAsDone(int index) {
        this.list.get(index).markDone();
        this.backend.save(this);
        System.out.println( "Nice! I've marked this task as done:\n"
                + list.get(index).toString());
    }
    public void unmarkTask(int index) {
        this.list.get(index).unmark();
        this.backend.save(this);
        System.out.println( "Nice! I've marked this task as done:\n"
                + list.get(index).toString());
    }

    public void deleteTask(int index) {
        this.list.remove(index);
        backend.save(this);
        System.out.println("Noted. I've removed this task:\n"
                + list.get(index).toString() );
        totalNumberOfTasks();
    }
    public void totalNumberOfTasks() {
        System.out.println("Now you have " + this.list.size() + " tasks in the list.");
    }

    public int size() {
        return this.list.size();
    }

    public Task getTask(int index) {
        int zeroBasedIndex = index - 1;
        return this.list.get(zeroBasedIndex);
    }

    public void inString() {
        System.out.println("Here are the tasks in your list:\n");
        for (int i = 0; i < this.list.size(); i++) {
            int index = i + 1;
            System.out.println(index + "." + this.list.get(i).toString());
        }
    }

}
