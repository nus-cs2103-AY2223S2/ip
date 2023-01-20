public class Task {
    String task;
    Boolean done = false;

    public Task(String task) {
        this.task = task;
    }
    public void mark() {
        this.done = true;
    }

    public void unmark() {
        this.done = false;
    }

    @Override
    public String toString() {
        String mark = this.done ? "X" : " ";
        return "[" + mark + "]" +  ' ' + this.task;
    }
}
