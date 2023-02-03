package page.quest;

public class Quest {

    private String description;

    private boolean isCompleted;

    public Quest(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    private String getCompletionIcon() {
        if (isCompleted) {
            return "[X]";
        } else {
            return "[_]";
        }
    }

    public void markComplete() {
        isCompleted = true;
    }

    public void markIncomplete() {
        isCompleted = false;
    }

    @Override
    public String toString() {
        return getCompletionIcon() + " " + description;
    }
}
