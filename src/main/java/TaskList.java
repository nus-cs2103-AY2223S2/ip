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

    public boolean checkMark(String mark) {
        if (mark.equals("X")) {
            return true;
        } else return false;
    }

    public void removeTask(int index) {
        this.tasks.remove(index);
    }

    public void addLine(String line) {
        if (line.substring(1,2).equals("T")) {
            String todo = line.substring(6);
            Todo newTodo = new Todo(todo, this.checkMark(line.substring(3,4)));
            this.addTask(newTodo);
        }
        if (line.substring(1,2).equals("D")) {
            String deadline = line.substring(6);
            Deadline newDead = new Deadline(deadline, this.checkMark(line.substring(3,4)));
            this.addTask(newDead);
        }
        if (line.substring(1,2).equals("E")) {
            String event = "event " + line.substring(6);
            Event newEvent = new Event(event, this.checkMark(line.substring(3,4)));
            this.addTask(newEvent);
        }
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