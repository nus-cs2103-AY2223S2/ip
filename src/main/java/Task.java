public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * marks the task as done
     */
    public void markDone() {
        this.isDone = true;
        String str = " Nice! I've marked this task as done!:\n" + "       " + this.toString();
        Duke.printWithLines(str);
    }

    /**
     * unmarks the tast
     */
    public void markUndone() {
        this.isDone = false;
        String str = " OK, I've marked this task as not done yet:\n" + "       " + this.toString();
        Duke.printWithLines(str);
    }

    /**
     * format for the task string
     * @return the string representing the task
     */
    @Override
    public String toString() {
        return (this.isDone ? "[X]" : "[ ]") + this.description;
    }

}
