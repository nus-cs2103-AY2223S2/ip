import java.util.ArrayList;
public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public void add(Task newTask) {
        this.taskList.add(newTask);
        System.out.println("    Got it. I've added this task:");
        System.out.println("     " + newTask.toString());
        System.out.println("    Now you have " + this.taskList.size() + " tasks in the list");
    }

    public void remove(int taskNumber) {
        System.out.println("    Noted. I've removed this task:");
        System.out.println("     " + this.taskList.get(taskNumber).toString());
        this.taskList.remove(taskNumber);
        System.out.println("    Now you have " + this.taskList.size() + " tasks in the list");
    }

    public void mark(int taskNumber) {
        this.taskList.get(taskNumber).mark();
        System.out.println("    OK, I've marked this task as done:");
        System.out.println("     " + this.taskList.get(taskNumber).toString());
    }

    public void unmark(int taskNumber) {
        this.taskList.get(taskNumber).unmark();
        System.out.println("    OK, I've marked this task as not done yet:");
        System.out.println("     " + this.taskList.get(taskNumber).toString());
    }

    public void list() {
        for (int i = 0; i < this.taskList.size(); i++) {
            int numbering = i + 1;
            System.out.println("    " + numbering + "." + taskList.get(i).toString());
        }
    }

    public ArrayList<Task> getList() {
        return this.taskList;
    }

}
