public class Task {
    private String content;
    private boolean done;

    Task(String content) {
        this.content = content;
        this.done = false;
    }

    public void mark() {
        this.done = true;
    }

    public void unmark() {
        this.done = false;
    }

    @Override
    public String toString() {
        String checkBox = this.done ? "[X] " : "[ ] ";
        return checkBox +  this.content;
    }
}