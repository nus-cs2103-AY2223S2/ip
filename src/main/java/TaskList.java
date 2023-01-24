import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Duke.Task> tasks;

    public TaskList() {
        tasks = new ArrayList<Duke.Task>();
    }

    public Duke.Task getTask(int index) {
        return this.tasks.get(index);
    }

    public int getSize() {
        return tasks.size();
    }

    public void addTask(Duke.Task task) {
        this.tasks.add(task);
    }

    public void removeTask(int index) {
        this.tasks.remove(index);
    }

    public String addReport(Duke.Task task) {
        String returnStr = "gotcha.\nyou added: " + task.toString().substring(2) + "\n"
                + this.numberOfTasks();
        return returnStr;
    }

    public String deleteReport(Duke.Task task) {
        String returnStr = "gotcha.\nyou you have deleted: " + task.toString().substring(2) + "\n"
                + this.numberOfTasks();
        return returnStr;
    }

    public void mark(String marked, int index) {
        this.tasks.get(index).setMark();
    }

    public String numberOfTasks() {
        return "You have " + this.tasks.size() + " tasks in this list!";
    }

    public String listThings() {
        String returnstr = "Alright, here are the things: \n";
        for (int i = 0; i < this.tasks.size(); i++) {
            if (i == this.tasks.size() - 1) {
                returnstr += Integer.toString(i+1) + this.tasks.get(i).toString();
            } else {
                returnstr += Integer.toString(i+1) + this.tasks.get(i).toString() + "\n";
            }
        } return returnstr;
    }

}