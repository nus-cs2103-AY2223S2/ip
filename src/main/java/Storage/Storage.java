package Storage;

import java.util.ArrayList;
import java.util.List;

public class Storage {

    private final List<Task> data;

    public Storage() {
        this.data = new ArrayList<>();
    }

    public void addTask(Task task) {
        data.add(task);
        System.out.println("Leo: added " + task.getTask() + " to your tasks :-) !");
    }

    public void showList() {
        int length = data.size();
        for (int i = 0; i < length; i++) {
            System.out.println((i + 1) + ". " + data.get(i).toString());
        }
    }

    public void mark(int num) {
        data.get(num - 1).mark();
        System.out.println("Good work! You have completed task " + num + ":");
        System.out.println(data.get(num-1).toString());
    }

    public void unmark(int num) {
        data.get(num - 1).unmark();
        System.out.println("No worries! I have unmarked task " + num + ":");
        System.out.println(data.get(num-1).toString());
    }

}
