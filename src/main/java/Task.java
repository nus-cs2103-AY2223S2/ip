public class Task {
    private String description;
    private boolean done;

    public Task(String description) {
        this.description = description;
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
            return String.format("[X] " + this.description);
        } else {
            return String.format("[ ] " + this.description);
        }
    }
}
