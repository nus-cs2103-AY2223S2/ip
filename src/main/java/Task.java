public class Task {
    private String name; // name of the task
    private Boolean status; // whether task is done or not

    public Task(String name) {
        this.name = name;
        this.status = false;
    }
    public void mark() {
        this.status = true;
    }

    public void unmark() {
        this.status = false;
    }

    @Override
    public String toString() {
        return this.status
                ? String.format("[X] %s", this.name)
                : String.format("[ ] %s", this.name);
    }

}
