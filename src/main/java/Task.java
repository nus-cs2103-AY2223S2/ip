public class Task {
    protected String name;
    protected boolean done;

    Task(String taskName) {
        this.name = taskName;
        done = false;
    }

    public void setDone() {
        done = true;
    }

    public void setUndone() {
        done = false;
    }

    public String getName() {
        return this.name;
    }

}
