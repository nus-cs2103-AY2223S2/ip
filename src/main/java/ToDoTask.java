public class ToDoTask {
    private boolean isDone;
    private String task;

    public ToDoTask(String task) {
        this.isDone = false;
        this.task = task;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getTask() {
        return this.task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.isDone ? "X" : " ", this.task);
    }
}
