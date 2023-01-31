import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public TaskList(List<Task> initList) {
        this.list = initList;
    }

    public Task getTask(int index) {
        return list.get(index);
    }

    public void addTask(Task task) {
        list.add(task);
    }

    public void deleteTask(int index) {
        list.remove(index);
    }

    public int getSize() {
        return list.size();
    }

    public List<Task> getTaskList() {
        return list;
    }

    public String getTaskListStr() {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n\t ");
        for (int i = 0; i < list.size(); i++) {
            int count = i + 1;
            String res = count + "." + list.get(i).toString();
            if (i != list.size() - 1) {
                res += "\n\t ";
            }
            sb.append(res);
        }
        return (sb.toString());
    }
}
