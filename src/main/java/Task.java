public class Task {

    private String task;
    private Boolean isDone;
    protected Character abbreviation;

    public Task(String task) {
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
        return  "[" + abbreviation + "]" + "[" + status + "] " + task;
    }
}
