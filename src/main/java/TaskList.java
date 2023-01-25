import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> tasks = new ArrayList<>();

    public TaskList() {
        //Default constructor for empty TaskList
    }

    //AddTask method to add ToDo
    public void addTask(String taskDesc) {
        tasks.add(new ToDo(taskDesc));
    }

    //AddTask method to add Deadline
    public void addTask(String taskDesc, String taskBy) {
        tasks.add(new Deadline(taskDesc, taskBy));
    }

    //AddTask method to add Deadline
    public void addTask(String taskDesc, String taskFrom, String taskTo) {
        tasks.add(new Event(taskDesc, taskFrom, taskTo));
    }

    public void printTask() {
        System.out.println("    ____________________________________________________________");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("     " + (i + 1) + ". " + tasks.get(i).toString());
        }
        System.out.println("    ____________________________________________________________");
    }
}
