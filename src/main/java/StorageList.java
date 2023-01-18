import java.util.ArrayList;

public class StorageList {
    private ArrayList<Task> list;

    public StorageList() {
        this.list = new ArrayList<>();
    }

    public void markTask(int taskno) {
        list.get(taskno).markAsDone();
        System.out.println(list.get(taskno));
    }

    public void unmarkTask(int taskno) {
        list.get(taskno).markAsUndone();
        System.out.println(list.get(taskno));
    }

    public void printList() {
        System.out.println("Here are the tasks in your list: ");
        for (int x = 0; x < list.size(); x++) {
            System.out.println((x + 1) + "." + list.get(x));
        }
        System.out.println("");
    }

    public void addTodo(String sentence) throws DukeException {
        Todo t = new Todo(sentence);
        list.add(t);
        System.out.println("Got it, I've added this task:");
        System.out.println(t);
    }

    public void addDeadline(String sentence, String by) throws DukeException {
        Deadline t = new Deadline(sentence, by);
        list.add(t);
        System.out.println("Got it, I've added this task:");
        System.out.println(t);
    }

    public void addEvent(String sentence, String from, String to) {
        Event t = new Event(sentence, from, to);
        list.add(t);
        System.out.println("Got it, I've added this task:");
        System.out.println(t);
    }

    public void deleteTask(int taskno) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(list.get(taskno));
        list.remove(taskno);

    }

    public int lengthOflist() {
        return list.size();
    }
}
