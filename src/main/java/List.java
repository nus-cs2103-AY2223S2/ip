import java.util.ArrayList;

public class List {
    private ArrayList<Task> list = new ArrayList<>();

    public List() {
        this.list.add(new ToDo("zeroth"));
    }

    public void add(String input) throws DukeException {
        if (isEmpty(input)) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        ToDo newTask = new ToDo(input);
        this.list.add(newTask);
        System.out.println("    Got it. I've added this task:");
        System.out.println("    " + newTask.toString());
        System.out.println("    Now you have " + (this.list.size() - 1) + " tasks in the list.");
    }

    public void add(String input, String deadline) throws DukeException {
        if (isEmpty(input) || isEmpty(deadline)) {
            throw new DukeException("The description and deadline of a deadline cannot be empty.");
        }
        Deadline newTask = new Deadline(input, deadline);
        this.list.add(newTask);
        System.out.println("    Got it. I've added this task:");
        System.out.println("    " + newTask.toString());
        System.out.println("    Now you have " + (this.list.size() - 1) + " tasks in the list.");
    }

    public void add(String input, String from, String to) throws DukeException {
        if (input.equals("") || from.equals("") || to.equals("")) {
            throw new DukeException("The descriptiom and duration of an event cannot be empty.");
        }
        Event newTask = new Event(input, from, to);
        this.list.add(newTask);
        System.out.println("    Got it. I've added this task:");
        System.out.println("    " + newTask.toString());
        System.out.println("    Now you have " + (this.list.size() - 1) + " tasks in the list.");
    }

    public boolean isEmpty(String str) {
        String temp = str.replaceAll("\\s+", "");
        if (temp.equals("")) {
            return true;
        }
        return false;
    }

    public void remove(int index) {
        Task removedTask = this.list.get(index);
        this.list.remove(index);
        System.out.println("    Noted. I've removed this task:");
        System.out.println("    " + removedTask.toString());
        System.out.println("    Now you have " + (this.list.size() - 1) + " tasks in the list.");
    }

    public Task get(int index) {
        return this.list.get(index);
    }

    public void print() {
        System.out.println("    Here are the tasks in your list:");
        for (int i = 1; i < this.list.size(); i++) {
            System.out.println("    " + i + ". " + this.list.get(i).toString());
        }
    }
}
