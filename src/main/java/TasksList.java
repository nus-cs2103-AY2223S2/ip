import java.util.ArrayList;

public class TasksList {
    private ArrayList<Tasks> list;
    
    public TasksList(int size) {
        list = new ArrayList<Tasks>(size);
    }

    public void addTask(Tasks task) {
        list.add(task);
    }

    public int getSize() {
        return list.size();
    }

    public Tasks getTask(int index) {
        return list.get(index);
    }

    public Tasks removeTask(int index) {
        return list.remove(index);
    }
}
