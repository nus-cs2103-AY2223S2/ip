import java.util.ArrayList;

public class data {
    ArrayList<Task> history = new ArrayList<>();

    public void addHist(Task task) {
        history.add(task);
        System.out.println("added: " + task.task);
    }

    public Task getHist(int pos) {
        return history.get(pos);
    }
    public void mark(int pos) { history.get(pos).mark(); }
    public void unmark(int pos) {
        history.get(pos).unmark();
    }
    public int getNum() { return history.size();}

    public void printHist() {
        for (int i = 0; i < history.size(); i++) {
            System.out.println((i+1) + ". " + history.get(i).toString());
        }
    }
}
