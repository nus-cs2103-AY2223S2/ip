public class Task {
    private boolean done;
    // private String type;

    private String description;

    public Task(String description) {
        this.description = description;
        this.done = false;
    }

    public String mark() {
        this.done = true;
        return this.toString();
    }

    public String unMark() {
        this.done = false;
        return this.toString();
    }

    @Override
    public String toString() {
        String doneString;
        if (done) {
            doneString = "[X]";
        } else {
            doneString = "[ ]";
        }

        return doneString + " " +description;
    }
}
