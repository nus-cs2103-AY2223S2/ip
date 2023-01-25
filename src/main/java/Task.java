public class Task {
    protected String desc;
    protected boolean isDone;

    public Task(String description) {
        this.desc = description;
        this.isDone = false;
    }
    public String getDesc() {
        return this.desc;
    }

    public void setStatus(Boolean isDone){
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.desc);
    }

    public String toBackup() {
        return String.format("%s | %s", (isDone ? "1" : "0"), this.desc);
    }
}
