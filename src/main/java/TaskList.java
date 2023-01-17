import java.util.*;

public class TaskList {
    private ArrayList<Task> list;
    public TaskList() {
        this.list = new ArrayList<>();
    }

    public void mark(int inputIndex) {
        this.list.get(inputIndex-1).mark();
    }
    
    public void unmark(int inputIndex) {
        this.list.get(inputIndex-1).unmark();
    }

    public void add(Task newTask) {
        this.list.add(newTask);
        System.out.println("Got it. I've added this task: " + newTask);
    }

    public void list() {
        System.out.println("Here are the task in your list: ");
        for(int i=1; i<list.size() + 1; i++) {
            System.out.println(i + "." + this.list.get(i-1));
        }
    }

    public void delete(int inputIndex) {
        System.out.println("Noted. I've removed this task:" + "\n" + list.get(inputIndex-1));
        this.list.remove(inputIndex-1);
        System.out.println("Now you have " + list.size() + " tasks in the list");
    }
}
