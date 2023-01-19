import java.util.ArrayList;

public class User {
    private ArrayList<Tasks> tasks = new ArrayList<Tasks>();
    public void addTask(Tasks task) {
        this.tasks.add(task);
    }
    public void listTasks() {
        int i = 1;
        for (Tasks tasks : tasks) {
            System.out.println(i + ". " + tasks.seeTaskContent());
            i++;
        }
    }
}
