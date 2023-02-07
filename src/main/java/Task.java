public abstract class Task {
    protected String description; // name of the task
    protected Boolean isMarked; // whether task is done or not

    public Task(String description) {
        this.description = description;
        this.isMarked = false;
    }

    public Task(boolean isMarked, String description) {
        this.description = description;
        this.isMarked = isMarked;
    }

    public void mark() {
        this.isMarked = true;
    }

    public void unmark() {
        this.isMarked = false;
    }

    public abstract String makeSaveFormat();

    @Override
    public String toString() {
        return this.isMarked
                ? String.format("[X] %s", this.description)
                : String.format("[ ] %s", this.description);
    }

}
