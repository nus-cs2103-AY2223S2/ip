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

    public void setStatus(String isDone){
        if (isDone.equalsIgnoreCase("mark")){
            this.isDone = true;
        }
        else if (isDone.equalsIgnoreCase("unmark")){
            this.isDone = false;
        }
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.desc);
    }
}
