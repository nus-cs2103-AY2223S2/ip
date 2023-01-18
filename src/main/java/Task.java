public class Task {

    private String taskName;
    private Boolean taskDone;

    public Task(String taskName) {
        this.taskName = taskName;
        this.taskDone = false;
    }

    public void markDone() {
        this.taskDone = true;
    }

    public void markUnDone() {
        this.taskDone = false;
    }

    @Override
    public String toString() {
        if (taskDone == false) {
            return "[ ] " + this.taskName;
        }
        return "[X] " + this.taskName;
    }

}
