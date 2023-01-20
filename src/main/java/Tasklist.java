import java.util.ArrayList;

public class Tasklist {

    private ArrayList<Task> tasks;

    public Tasklist() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(String name) {
        tasks.add(new Task(name));
    }

    public void viewList() {
        for (int i = 0; i < this.tasks.size(); i++) {
            System.out.println("\t " + String.valueOf(i+1) + ". " +  this.tasks.get(i));
        }
    }
}
