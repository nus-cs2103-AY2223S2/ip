public class TaskList {

    /** An array to store the items in the queue. */
    private Task[] items;

    /** Index of the first element in the queue. */
    private int first;

    /** Index of the last element in the queue. */
    private int last;

    /** Maximum size of the queue. */
    private int maxSize;

    /** Number of elements in the queue. */
    private int len;

    /**
     * Constructor for a queue.
     *
     * @param size The maximum num of elements we can put in the queue.
     */
    public TaskList(int size) {
        this.maxSize = size;
        this.items = new Task[size];
        this.first = -1;
        this.last = -1;
        this.len = 0;
    }

    /**
     * Add the object e into the queue.
     *
     * @param e The item to put in the queue.
     * @return false if the queue is full; true if e is added successfully.
     */
    public boolean enq(Task e) {
        if (this.isFull()) {
            return false;
        }
        if (this.isEmpty()) {
            this.first = 0;
            this.last = 0;
        } else {
            this.last = (this.last + 1) % this.maxSize;
        }
        this.items[last] = e;
        this.len += 1;
        System.out.println("added: " + e.getDescription());
        System.out.println("There are now " + this.len + " tasks to get done");
        return true;
    }

    /**
     * Remove the object from the queue.
     *
     * @return null if the queue is empty; the object removed from the queue otherwise.
     */
    public Object deq() {
        if (this.isEmpty()) {
            return null;
        }
        Object item = this.items[this.first];
        this.first = (this.first + 1) % this.maxSize;
        this.len -= 1;
        return item;
    }

    /**
     * Checks if the queue is full.
     *
     * @return true if the queue is full; false otherwise.
     */
    boolean isFull() {
        return (this.len == this.maxSize);
    }

    /**
     * Checks if the queue is empty.
     *
     * @return true if the queue is empty; false otherwise.
     */
    boolean isEmpty() {
        return (this.len == 0);
    }

    /**
     * Return the number of elements in the queue.
     *
     * @return The number of elements in the queue.
     */
    public int length() {
        return this.len;
    }

    /**
     * Returns the string representation of the queue.
     *
     * @return A string consisting of the string representation of
     * every object in the queue.
     */
    @Override
    public String toString() {
        String str = "This is what you need to get done:\n";
        int i = this.first;
        int count = 0;
        while (count < this.len) {
            int counter = count + 1;
            str += counter + ". " + this.items[i] + " \n";
            i = (i + 1) % this.maxSize;
            count++;
        }
        return str;
    }

    public Task indexof(int index) {
        return items[index];
    }
}

