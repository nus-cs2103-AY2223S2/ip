import java.util.ArrayList;

public class DukeList {
    private ArrayList<Task> list = new ArrayList();

    public DukeList() {}

    public void add(String type, String s) {
        System.out.println("Sure, Imma add that real quick");

        Task task;
        if (type.equals("todo")) {
            task = new Todo(s);

        } else if (type.equals("deadline")) {
            String[] arr = s.split(" /by", 2);
            task = new Deadline(arr[0], arr[1]);
        } else {
            String[] arr = s.split(" /from | /to", 3);
            task = new Event(arr[0], arr[1], arr[2]);
        }
        System.out.println(task.toString());
        list.add(task);
        int size = list.size();
        String plural;
        if (size == 1) {
            plural = " task.";
        } else {
            plural = " tasks.";
        }
        System.out.println("Now you've got " + list.size() + plural);
        System.out.println(new TextBorder(""));
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void findAndMark(String text, boolean mark) {
        for (Task t : list) {
            if (t.isCorrectTask(text)) {
                t.markOut(mark);
                return;
            }
        }
        System.out.println("Sorry, can't find the task!");
    }

    @Override
    public String toString() {
        String out = "";
        int num = 1;
        for (Task t: list) {
            out += num + ". " + t.toString() + "\n";
            num ++;
        }
        return out;
    }
}
