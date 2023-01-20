public class Task {
    private String input;
    private boolean status;

    public Task(String input) {
        this.input = input;
        this.status = false;
    }
    public void mark() {
        this.status = true;
    }
    public void unmark() {
        this.status = false;
    }

    public String toString() {
        if (this.status) {
            return String.format("[X] " + this.input);
        } else {
            return String.format("[ ] " + this.input);
        }
    }

}
