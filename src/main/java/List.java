import java.util.ArrayList;

public class List {
    private ArrayList<Task> list = new ArrayList<>();

    public List() {
        list.add(new Task("zeroth"));
    }

    public void add(String input) {
        Task newTask = new Task(input);
        list.add(newTask);
        System.out.println("    added: " + newTask.getTask());
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
