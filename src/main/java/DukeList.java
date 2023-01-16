import java.util.ArrayList;

public class DukeList {

    public ArrayList<String> list = new ArrayList<>();

    public ArrayList<String> getList() {
        return list;
    }

    public void addTask(String task) {
        list.add(Integer.toString((list.size())) + ". " + task);
    }
}
