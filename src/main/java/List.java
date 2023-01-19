import java.util.ArrayList;

public class List {
    private ArrayList<Task> list = new ArrayList<>();

    public List() {
        list.add(new ToDo("zeroth"));
    }

    public void add(String input) {
        ToDo newTask = new ToDo(input);
        list.add(newTask);
        System.out.println("    Got it. I've added this task:");
        System.out.println("    " + newTask.toString());
        System.out.println("    Now you have " + (list.size() - 1) + " tasks in the list.");
    }

    public void add(String input, String deadline) {
        Deadline newTask = new Deadline(input, deadline);
        list.add(newTask);
        System.out.println("    Got it. I've added this task:");
        System.out.println("    " + newTask.toString());
        System.out.println("    Now you have " + (list.size() - 1) + " tasks in the list.");
    }

    public void add(String input, String from, String to) {
        Event newTask = new Event(input, from, to);
        list.add(newTask);
        System.out.println("    Got it. I've added this task:");
        System.out.println("    " + newTask.toString());
        System.out.println("    Now you have " + (list.size() - 1) + " tasks in the list.");
    }

    public Task get(int index) {
        return list.get(index);
    }

    public void print() {
        System.out.println("    Here are the tasks in your list:");
        for (int i = 1; i < list.size(); i++) {
            System.out.println("    " + i + ". " + list.get(i).toString());
        }
    }
}
