import java.util.ArrayList;

public class Data {
    ArrayList<Task> data = new ArrayList<>();

    public void addEntry(Task task) {
        this.data.add(task);
        System.out.println("added: " + task.description);
    }

    public void addFileEntry(Task task) {
        this.data.add(task);
    }

    public Task getEntry(int pos) {
        return this.data.get(pos);
    }
    public void removeEntry(int pos) { this.data.remove(pos);}
    public void mark(int pos) { this.data.get(pos).mark(); }
    public void unmark(int pos) {
        this.data.get(pos).unmark();
    }
    public int getSize() { return this.data.size();}

    public void printData() {
        for (int i = 0; i < this.data.size(); i++) {
            System.out.println((i+1) + ". " + this.data.get(i).toString());
        }
    }
}
