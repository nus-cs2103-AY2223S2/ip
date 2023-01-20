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
        for (Task t : tasks) {
            System.out.println(t);
        }
    }
}
