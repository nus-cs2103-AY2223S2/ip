public class Task {
    private boolean isMarked;
    private String taskName;

    public Task(String name) {
        this.isMarked = false;
        this.taskName = name;
    }

    public void markTask() {
        this.isMarked = true;
    }

    public void unmarkTask() {
        this.isMarked = false;
    }

    public String getName() {
        return this.taskName;
    }

    @Override
    public String toString() {
        if (this.isMarked == true) {
            return ("[X] " + this.taskName);
        } else {
            return ("[ ] " + this.taskName);
        }
    }
}
