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
     * Checks the status of the task and logs to stdout.
     */
    public void mark() {
        this.isDone = true;
        System.out.println("Nice! Marked this task as done, I have:");
        System.out.println(String.format(" [%s] %s", this.getStatusIcon(), this.description));
    }

    /**
     * Unchecks the status of the task and logs to stdout.
     */
    public void unmark() {
        this.isDone = false;
        System.out.println("Ok! Marked this task as not done yet, I have:");
        System.out.println(String.format(" [%s] %s", this.getStatusIcon(), this.description));
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the string representation of a task.
     * @return string representation of a task
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }

}
