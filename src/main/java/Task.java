public class Task {
    private String s = "";
    private boolean isDone;

    public Task(String s) {
        this.s = s;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return "[X] " + s;
        } else {
            return "[ ] " + s;
        }
    }
}
