package Storage;

import java.util.ArrayList;
import java.util.List;

public class Storage {

    private List<String> data;

    public Storage() {
        this.data = new ArrayList<>();
    }

    public void addTask(String task) {
        data.add(task);
        System.out.println("Leo: added " + task + " to your tasks :-) !");
    }

    public void showList() {
        int length = data.size();
        for (int i = 0; i < length; i++) {
            System.out.println((i + 1) + ". " + data.get(i));
        }
    }
}
