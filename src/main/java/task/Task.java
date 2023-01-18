package task;

public class Task {
    protected String desc;
    protected boolean isMarked;
    protected static int count = 0;

    public Task(String desc) {
        this.desc = desc;
        this.isMarked = false;
        Task.count ++;

    }

    public void mark() {
        isMarked = true;
    }

    public void unMark() {
        isMarked = false;
    }

    public static int getCount() {
        return Task.count;
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
