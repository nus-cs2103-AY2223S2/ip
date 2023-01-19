public abstract class Task {

    private boolean isDone;
    private String name;

    public Task(String name) {
        this.isDone = false;
        this.name = name;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmarkAsDone() {
        this.isDone = false;
    }

    protected String getStatusIcon() {
        return (isDone ? "x" : " ");
    }

    protected abstract String getTaskType();

    @Override
    public String toString() {
        String s = String.format(
            "[%s][%s] %s",
            this.getTaskType(),
            this.getStatusIcon(),
            this.name
        );
        return s;
    }
}
