public class Task {
    private String desc;
    private boolean done;

    public Task(String desc) {
        this.desc = desc;
        setDone(false);
    }

    public String getDescription() {
        return this.desc;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public boolean isDone() {   return this.done;   }

}
