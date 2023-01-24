import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> storeList;

    public TaskList(ArrayList<Task> initList) {
        storeList = initList;
    }


    public void addTask(Task task) {
        storeList.add(task);
    }

    public void removeListMember(int index) {
        storeList.remove(index - 1);
    }

    public Task getTask(int index) {
        return storeList.get(index - 1);
    }

    public ArrayList<Task> getList() {
        return storeList;
    }
}
