import java.util.ArrayList;
public class TaskList {
    private ArrayList<Task> storer;

    TaskList(ArrayList<Task> storer) {
        this.storer = storer;
    }
    TaskList() {
        this.storer = new ArrayList<Task>();
    }
    public void add(Task taskNew) {
        storer.add(taskNew);
    }

    public int size() {
        return this.storer.size();
    }

    public String getTaskStrings() throws StorerEmptyException {

        String concat = "";
        for (int i = 1; i <= this.storer.size(); i++) {
            int j = i - 1;
            concat = concat + "\n" + i + ". " + this.storer.get(j);

        }

        if (concat == "") {
            throw new StorerEmptyException();
        }
        return concat;
    }

    public ArrayList<Task> getStorer() {
        return this.storer;
    }
    public void markTask(int index) {
        this.storer.get(index - 1).mark();
    }

    public Task get(int index) {
        return this.storer.get(index - 1);
    }

    public Task remove(int index) {
        return this.storer.remove(index - 1);
    }

}
