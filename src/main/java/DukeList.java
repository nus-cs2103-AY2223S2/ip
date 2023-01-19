import java.util.ArrayList;

public class DukeList {
    private ArrayList<Task> list = new ArrayList<>();

    public DukeList() {}

    private String pluralTask (int size) {
        if (size == 1) {
            return " task.";
        } else {
            return " tasks.";
        }
    }

    public void add(String type, String s) {


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
        System.out.println("Sure, Imma add that real quick");
        System.out.println(task.toString());
        list.add(task);
        System.out.println("Now you've got " + list.size() + pluralTask(list.size()));
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

    public void delete (int i) throws TaskOutOfRangeException{
        if (i > this.list.size() || i < 0) {
            throw new TaskOutOfRangeException("Yo, I can't find the task at " + i);
        } else {
            Task removedTask = this.list.remove(i - 1);
            System.out.println("Got it, this task is gonez:");
            System.out.println(removedTask);
            System.out.println("Now you've got " + list.size() + pluralTask(list.size()));
        }
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
