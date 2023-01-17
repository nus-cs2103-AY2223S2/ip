public class Task {
    protected String description;
    protected boolean isDone;
    private static int count = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        count++;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    public static int getCount() {
        return count;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
