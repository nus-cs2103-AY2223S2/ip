import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;

    public TaskList() {
        list = new ArrayList<>();
    }

    // Check if index is in list
    private boolean isInvalidIndex(int index) {
        return index <= 0 || index > list.size();
    }

    public void add(Task t) {
        list.add(t);
    }

    // list is 0-indexed
    public void mark(int index) throws BobException {
        if (isInvalidIndex(index)) {
            String error = String.format("Index given should be in range [1-%s]", list.size());
            throw new BobException(error);
        }

        list.get(index - 1).mark();
    }

    // list is 0-indexed
    public void unmark(int index) throws BobException {
        if (isInvalidIndex(index)) {
            String error = String.format("Index given should be in range [1-%s]", list.size());
            throw new BobException(error);
        }
        list.get(index - 1).unmark();
    }

    public Task delete(int index) throws BobException {
        if (isInvalidIndex(index)) {
            String error = String.format("Index given should be in range [1-%s]", list.size());
            throw new BobException(error);
        }

        return list.remove(index - 1);
    }

    public Task get(int index) throws BobException {
        if (isInvalidIndex(index)) {
            String error = String.format("Index given should be in range [1-%s]", list.size());
            throw new BobException(error);
        }

        return list.get(index - 1);
    }

    public ArrayList<Task> getList() {
        return this.list;
    }
}
