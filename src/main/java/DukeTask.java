public class DukeTask {

    private String task;
    private Boolean isDone;

    public DukeTask(String task) {
        this.task = task;
        this.isDone = false;
    }

    public void mark() {
        isDone = true;
    }

    public void unmark() {
        isDone = false;
    }

    @Override
    public String toString() {
        String status = " ";
        if (isDone) {
            status = "X";
        }
        return "[" + status + "] " + task;
    }
}
