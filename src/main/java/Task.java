public class Task {

    private String task;
    private Boolean isDone;
    protected Character abbreviation;

    public Task(String task) throws Exception {
        if (task == "") {
            throw new Exception("OOPS!!! The description of a task cannot be empty.");
        }
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
