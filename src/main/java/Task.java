public class Task {
    private String input;
    private boolean done;

    public Task(String input) {
        this.input = input;
        this.done = false;
    }

    public void mark(){
        if (!this.done) {
            this.done = true;
        }
    }
    public void unmark(){
        if (this.done) {
            this.done = false;
        }
    }
    @Override
    public String toString() {
        if (this.done) {
            return String.format("[X] " + this.input);
        } else {
            return String.format("[ ] " + this.input);
        }
    }
}
