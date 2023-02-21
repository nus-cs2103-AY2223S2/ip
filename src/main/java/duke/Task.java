package duke;

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

    public int getMarkedStatus() {
        return this.isDone() ? 1 : 0;
    }

    public void setMarked() {
        this.done = true;
    }

    public void setUnmarked() {
        this.done = false;
    }

    public String getMessageWhenAdded() {
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
