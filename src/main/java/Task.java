public class Task {

    public static Integer count = 0;

    protected String taskName;
    protected  Boolean taskDone;

    protected Task(String taskName) {
        this.taskName = taskName;
        this.taskDone = false;
        Task.count++;
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
