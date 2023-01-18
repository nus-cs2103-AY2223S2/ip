public class Task {
    private String description;
    private boolean done;

    public Task(String description) {
        this.description = description;
        // assumed to be not complete upon initialisation
        // would not make sense to add a finished task to the list
        this.done = false;
    }

    public void complete() {
        this.done = true;
    }

    public void uncomplete() {
        this.done = false;
    }

    @Override
    public String toString() {
        return (this.done ? "[X] " : "[ ] ") + this.description;
    }
}
