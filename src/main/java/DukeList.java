import java.util.ArrayList;

public class DukeList {
    ArrayList<Task> list = new ArrayList();

    public DukeList() {}

    public void add(String s) {
        list.add(new Task(s));
        System.out.println(new TextBorder("added: " + s));
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
