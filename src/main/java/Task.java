public class Task {
    public boolean done;
    public String title;

    public Task(String title) {
        this.done = false;
        this.title = title;
    }

    private void setDone(boolean done) {
        this.done = done;
    }

    public void completed() {
        setDone(true);
    }

}
