import java.util.*;

public class TaskList {
    protected LinkedList<Task> lst;
    protected static String line = "____________________________________________________________";

    public TaskList() {
        this.lst = new LinkedList<>();
    }

    public void printList() {
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < lst.size(); i++) {
            String elem = lst.get(i).toString();
            System.out.println(String.format("%d. %s", i + 1, elem));
        }
    }

    public Task getTask(int index) {
        return this.lst.get(index);
    }

    public void addTask(Task t) {
        this.lst.add(t);
    }

    public int getSize() {
        return this.lst.size();
    }

    public void printSize() {
        System.out.println(String.format(
                "Now you have %d tasks in the list!", this.getSize()));
    }

    public static void printLine(){
        System.out.println(line);
    }

}
