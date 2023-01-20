import java.util.ArrayList;

public class Tasklist {

    private ArrayList<Task> tasks;

    public Tasklist() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task t) {
        tasks.add(t);
        Duke.printOutput(
                "\t I've added the following to your list of tasks: \n\t\t" +
                t + "\n\t You now have " + this.tasks.size() + " task(s) in the list.");
    }

    public Task get(int index) {
        return this.tasks.get(index);
    }

    public boolean mark(int index) {
        return this.tasks.get(index).markAsDone();
    }

    public boolean unmark(int index) {
        return this.tasks.get(index).markAsUndone();
    }

    public void viewList() {
        if (this.tasks.isEmpty()) {
            System.out.println("\t You currently have no tasks.");
        } else {
            System.out.println("\t Here is a list of your tasks: ");
            for (int i = 0; i < this.tasks.size(); i++) {
                System.out.println("\t " + String.valueOf(i+1) + "." +  this.tasks.get(i));
            }
        }

    }
}
