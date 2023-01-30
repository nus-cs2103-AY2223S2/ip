package Duke.Tasks;

public class Task {
    protected String description;
    protected boolean isComplete;

    public Task(String description) {
        this.description = description;
        this.isComplete = false;
    }

    public void markDone() {
        this.isComplete = true;
    }

    public void markNotDone() {
        this.isComplete = false;
    }

    public String getDescription() {
        return this.description;
    }

    public String getTaskStatus() {
        return isComplete ? "X" : " ";
    }


    public String printTask() {
        return String.format("NA | %d | %s ", isComplete ? 1 : 0, description);
    }

    @Override
    public String toString() {
        return "[" + this.getTaskStatus() + "] " + this.description;
    }
}
