public class Task {
    private boolean isMarked;
    private String taskName;

    public Task(String name) {
        this.isMarked = false;
        this.taskName = name;
    }

    public Task() {
        this.isMarked = false;
        this.taskName = "Blank Task";
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

    public String toLog() {
        if (this.isMarked) {
            return (" | 1 | " + this.taskName);
        } else {
            return(" | 0 | " + this.taskName);
        }
    }

    @Override
    public String toString() {
        if (this.isMarked) {
            return ("[X] " + this.taskName);
        } else {
            return ("[ ] " + this.taskName);
        }
    }
}
