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
    public boolean isDone() {
        return this.isDone;
    }

    private String status() {
        if (this.isDone) {
            return "X";
        } else {
            return " ";
        }
    }

    public static Task parse(String str) {
        Task task;
        String[] strArray = str.split("] ");
        switch (strArray[0].charAt(0)) {
        case 'T':
            task = new Todo(strArray[2]);
        default:
            task = new Task(strArray[1]);
        }
        return task;
    }

    @Override
    public String toString() {
        return "[" + this.status() + "] " + this.name;
    }
}
