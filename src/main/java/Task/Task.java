package Task;

public class Task {
    private String description;
    private boolean isMark;

    public Task(String description) {
        this.description = description;
        this.isMark = false;
    }

    public String getDescription() {
        return description;
    }

    public Character getMark() {
        return isMark ? 'X' : ' ';
    }

    public void toggleMark() {
        this.isMark = !this.isMark;
    }

    @Override
    public String toString() {
        return String.format("[%c] %s", getMark(), this.description);
    }
}