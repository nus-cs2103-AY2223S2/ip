public class Task {
    private boolean isDone;
    private String title;
    public Task(String title) {
        this.title = title;
        this.isDone = false;
    };

    public void setIsDone(boolean status) {
        this.isDone = status;
    }
    @Override
    public String toString() {
        return String.format("[%s] %s", isDone ? "x" : " ", this.title);
    }

}
