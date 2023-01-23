import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;
    private Storage storage;

    public TaskList() {
        this.list = new ArrayList<>();
        this.storage = new Storage();
    }

    public void mark(int inputIndex) {
        this.list.get(inputIndex-1).mark();
        storage.save(this);
    }
    
    public void unmark(int inputIndex) {
        this.list.get(inputIndex-1).unmark();
        storage.save(this);
    }

    public void add(Task newTask) {
        this.list.add(newTask);
        System.out.println("Got it. I've added this task: " + newTask);
        storage.save(this);
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
        storage.save(this);
    }

    public String get(int inputIndex) {
        return (inputIndex + "." + this.list.get(inputIndex-1));
    }

    public int size() {
        return list.size();
    }
}
