public class Task {
    private boolean done = false;
    private String name;

    Task(String name) {
        this.name = name;
    }

    /**
     * Marks the task done
     */
    public void mark() {done = true;}

    /**
     * Marks the task not done
     */
    public void unmark() {done = false;}

    /**
     * Returns the name of the task
     * @return the name of the task
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the status of the task
     * @return the status of the task - done or not
     */
    public boolean isDone() {
        return this.done;
    }

    private String status() {
        if (done) {
            return "X";
        } else {
            return " ";
        }
    }

    public String toString() {
        return "[" + this.status() + "] " + this.name;
    }
}
