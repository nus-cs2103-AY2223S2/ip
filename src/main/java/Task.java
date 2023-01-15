public class Task {
    private String desc;
    private boolean isDone;
    public Task (String desc) {
        this.desc = desc;
        this.isDone = false;
    }
    public String getStatusIcon() {
        return this.isDone ? "[X]" : "[]" ;
    }
}
