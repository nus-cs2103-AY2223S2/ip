import java.util.*;
public class Tasklist {
    private ArrayList<Task> list;
    public Tasklist() {
        this.list = new ArrayList<>();
    }
    public void add(Task task) {
        this.list.add(task);
        System.out.println("Got it. I've added this task:\n " + task.toString());
    }
    public void markTaskAsDone(int index) {
        this.list.get(index).markDone();
        System.out.println( "Nice! I've marked this task as done:\n"
                + list.get(index).toString());
    }
    public void unmarkTask(int index) {
        this.list.get(index).unmark();
        System.out.println( "Nice! I've marked this task as done:\n"
                + list.get(index).toString());
    }

    public void deleteTask(int index) {
        this.list.remove(index);
        System.out.println("Noted. I've removed this task:\n"
                + list.get(index).toString() );
        totalNumberOfTasks();
    }
    public void totalNumberOfTasks() {
        System.out.println("Now you have " + this.list.size() + " tasks in the list.");
    }

    public void inString() {
        System.out.println("Here are the tasks in your list:\n");
        for (int i = 0; i < this.list.size(); i++) {
            int index = i + 1;
            System.out.println(index + "." + this.list.get(i).toString());
        }
    }

}
