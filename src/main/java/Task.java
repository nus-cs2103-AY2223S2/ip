public class Task {
    private String description;
    private boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
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

    public String stringFormatForFile() {
        return ((this.isDone ? "1" : "0") + " / " + this.description.trim()).trim();
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
