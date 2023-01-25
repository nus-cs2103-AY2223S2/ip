import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> tasks;

    public TaskList() {
        //Default constructor for empty TaskList
        tasks = new ArrayList<Task>();
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

    public void deleteTask(int index) {
        Task t = tasks.get(index - 1);
        tasks.remove(index - 1);
        System.out.println(t + " is now gone!");
    }

    public void markTask(int index) {
        tasks.get(index - 1).markDone();
        System.out.println("I marked this task as done:\n" + tasks.get(index - 1));
    }

    public void unMarkTask(int index) {
        tasks.get(index - 1).markUndone();
        System.out.println("I marked this task as undone:\n" + tasks.get(index - 1));
    }

    public void printNewestTask() {
        System.out.println("Task added:\n"
                + tasks.get(tasks.size() - 1)
                + "\nTotal task now: "
                + tasks.size());
    }
}
