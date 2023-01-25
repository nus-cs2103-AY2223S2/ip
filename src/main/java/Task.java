public class Task {
    protected final String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    // I remember there's a modifier only allowing classes in same file to access?
    public void markAsDone() {
        this.isDone = true;
    }

    public void unmarkTask() {
        this.isDone = false;
    }

    public String addToFile() {
        String str = String.format(" | %d | %s ",
                isDone ? 1 : 0, this.description);
        return str + "\n";
    }

    @Override
    public String toString() {
        return String.format("[T][%s] %s",
                this.isDone ? "X" : " ", this.description);
    }
}
