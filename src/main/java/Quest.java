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
        if (isCompleted) {
            // System.out.println("Quest is already complete!\n" + toString());
        } else {
            isCompleted = true;
        }
    }

    public void markIncomplete() {
        if (isCompleted) {
            isCompleted = false;
        } else {
            // System.out.println("Quest is already incomplete...\n" + toString());
        }
    }

    @Override
    public String toString() {
        return getCompletionIcon() + " " + description;
    }
}
