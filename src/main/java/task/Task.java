package task;

public abstract class Task {
    protected String desc;
    protected boolean isMarked;

    public Task(String desc) {
        this.desc = desc;
        this.isMarked = false;
    }

    public void mark() {
        isMarked = true;
    }

    public void unMark() {
        isMarked = false;
    }

    public boolean isMarked() {
        return this.isMarked;
    }

    @Override
    public String toString() {
        if (isMarked) {
            return "[X] " + desc;
        } else {
            return "[ ] " + desc;
        }
    }

}
