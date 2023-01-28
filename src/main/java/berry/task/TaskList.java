package berry.task;

import java.util.ArrayList;

/**
 * Contains the task list.
 */
public class TaskList {

    private static String HOR_BAR = "✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦";

    private static ArrayList<Task> listOfTasks;

    public TaskList() {
        this.listOfTasks = new ArrayList<Task>();
    }
    public TaskList(ArrayList<Task> listOfTasks) {
        this.listOfTasks = listOfTasks;
    }

    public ArrayList<Task> getList() {
        return listOfTasks;
    }

    public void addTask(Task task) {
        listOfTasks.add(task);
        System.out.println(task.toString());
    }

    public void deleteTask(int index){
        System.out.println(listOfTasks.get(index - 1).toString());
        listOfTasks.remove(index - 1);
        printNumberOfTasks();
    }

    public void markDone(int index) {
        Task t = listOfTasks.get(index - 1);
        t.markAsDone();
    }

    public void markNotDone(int index) {
        Task t = listOfTasks.get(index - 1);
        t.markAsNotDone();
    }

    public boolean isIndexWithinRange(int index) {
        if (index > 0 && index <= listOfTasks.size()) {
            return true;
        }
        return false;
    }

    public void printNumberOfTasks() {
        System.out.println("You now have " + listOfTasks.size() + " tasks in the list ૮꒰ˊᗜˋ* ꒱ა");
    }
}