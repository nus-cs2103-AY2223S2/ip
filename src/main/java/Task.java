public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * This method prints the task status and description.
     *
     * @return  void
     */
    public void printTask() {
        System.out.println("[" + this.getStatusIcon() + "] "
                + this.description);
    }
}
