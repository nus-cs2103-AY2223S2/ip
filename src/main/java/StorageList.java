import java.util.ArrayList;

public class StorageList {
    private ArrayList<Task> list;

    public StorageList() {
        this.list = new ArrayList<>();
    }

    public void addTask(String sentence) {
        Task t = new Task(sentence);
        list.add(t);
    }

    public void markTask(int taskno) {
        list.get(taskno).markAsDone();
    }

    public void unmarkTask(int taskno) {
        list.get(taskno).markAsUndone();
    }

    public void printList() {
        System.out.println("Here are the tasks in your list: ");
        for (int x = 0; x < list.size(); x++) {
            System.out.println((x + 1) + "." + " [" + list.get(x).getStatusIcon() + "]" + " " + list.get(x));
        }
    }
}
