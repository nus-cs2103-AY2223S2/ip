public abstract class Task {
    private String name;
    private Boolean done;

    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    public Task(String name, boolean isDone) {
        this.name = name;
        this.done = isDone;
    }

    public String getName() {
        return this.name;
    }

    public Boolean isDone() {
        return this.done;
    }

    public int isDoneStatus() {
        return this.isDone() ? 1 : 0;
    }

    public void markAsDone() {
        this.done = true;
    }

    public void unmark() {
        this.done = false;
    }

    public String messageWhenAdded() {
        return "DukeyList just added a new item: ";
    }

    public abstract String getLogString();


    @Override
    public String toString() {
        if (this.isDone()) {
            return "[X] " + this.getName();
        }
        return "[ ] " + this.name;
    }



}
