public class Task {
    private boolean isDone = false;
    private String name;

    Task(String name) {
        this.name = name;
    }

    /**
     * Marks the task done
     */
    public void mark() {
        isDone = true;}

    /**
     * Marks the task not done
     */
    public void unmark() {
        isDone = false;}

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
    public boolean getIsDone() {
        return this.isDone;
    }

    private String status() {
        if (this.isDone) {
            return "X";
        } else {
            return " ";
        }
    }


    public String toString() {
        return "[" + this.status() + "] " + this.name;
    }
}
