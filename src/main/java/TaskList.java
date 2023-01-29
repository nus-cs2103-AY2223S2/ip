import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> store;
    public TaskList() {
        store = new ArrayList<>();
    }
    public int getSize() {
        return store.size();
    }

    public void listTask() {
        int number = 1;
        for (Task stored : store) {
            System.out.println(number + ". " + stored.toString());
            number++;
        }
    }

    public void addTask(Task task) {
        store.add(task);
    }

}
