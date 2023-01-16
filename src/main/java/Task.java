public class Task {
    private String desc;
    private boolean isDone;
    public Task (String desc) {
        this.desc = desc;
        this.isDone = false;
    }
    private String getStatusIcon() {
        return this.isDone ? "[X]" : "[]" ;
    }

    private String getType() {
        return "";
    }

    @Override
    public String toString() {
        return String.format("%s %s %s",getType(), getStatusIcon(), this.desc);
    }
    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }
}
