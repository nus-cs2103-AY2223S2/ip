public class Task {

    private String name;
    private boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public boolean markAsDone() {
        if (!this.isDone) {
            this.isDone = true;
            return true;
        }
        return false;
    }

    public boolean markAsUndone() {
        if (this.isDone) {
            this.isDone = false;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        String mark;
        if (this.isDone) {
            mark = "[X]";
        } else {
            mark = "[ ]";
        }
        return mark + " " + this.name;
    }
}
