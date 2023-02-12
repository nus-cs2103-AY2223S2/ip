public abstract class Task {
    private boolean isDone;
    private final String str;

    public Task(String str) {
        this.str = str;
        this.isDone = false;
    }

    /**
     * This marks the task as done
     * 
     * @return String form of task
     */
    public String mark() {
        this.isDone = true;
        return this.toString();
    }

    /**
     * This marks the task as undone
     * 
     * @return String form of task
     */
    public String unmark() {
        this.isDone = false;
        return this.toString();
    }

    public String getMarkX() {
        return (isDone ? "X" : " ");
    }

    public String getStr() {
        return this.str;
    }

    public abstract String getType();

    /**
     * Custom toString includes checkbox showing whether task is done
     * 
     * @return custom toString
     */
    @Override
    public String toString() {
        String checkbox;
        if (this.isDone) {
            checkbox = "[X] ";
        } else {
            checkbox = "[ ] ";
        }
        return checkbox + this.str;
    }
}
