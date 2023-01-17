import java.util.ArrayList;

public class Storage {
    protected ArrayList<String> tasks;

    public Storage() {
        this.tasks = new ArrayList<String>();
    }

    public void addTask(String name) {
        this.tasks.add(name);
        System.out.println("added: " + name);
    }

    public void showTasks() {
        for (int i = 0; i < this.tasks.size(); i++) {
            System.out.println(Integer.toString(i+1) + ". " + this.tasks.get(i));
        }
    }
}
