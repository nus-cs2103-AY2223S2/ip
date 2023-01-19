public class Task {
    private boolean done;
    private String title;

    public Task(String title) {
        this.done = false;
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String newTitle) {
        this.title = newTitle;
    }

    public boolean getDone() {
        return this.done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

}
