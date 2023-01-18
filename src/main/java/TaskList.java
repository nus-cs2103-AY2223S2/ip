import java.util.ArrayList;

public class TaskList {

    /** An array to store the items in the queue. */
    private ArrayList<Task> items;


    /**
     * Constructor for a queue.
     *
     * @param size The maximum num of elements we can put in the queue.
     */
    public TaskList(int size) {
        this.items = new ArrayList<Task>(size);
    }

    /**
     * Add the object e into the queue.
     *
     * @param e The item to put in the queue.
     * @return false if the queue is full; true if e is added successfully.
     */
    public boolean enq(Task e) {
        items.add(e);
        System.out.println("New Task Added: " + e.getDescription());
        System.out.println("There are now " + this.items.size() + " task(s) awaiting completion\n");
        return true;
    }

    public void delete(int index) {
        this.items.remove(index - 1);
    }

    /**
     * Returns the string representation of the queue.
     *
     * @return A string consisting of the string representation of
     * every object in the queue.
     */
    @Override
    public String toString() {
        String str = "This is your current task list:\n";
        int count = 0;
        while (count < this.items.size()) {
            int counter = count + 1;
            str += counter + ". " + this.items.get(count) + " \n";
            count++;
        }
        return str;
    }

    public Task indexof(int index) {
        return items.get(index);
    }
}

