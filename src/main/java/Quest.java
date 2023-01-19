public class Quest {
    private String description;
    private boolean isCompleted;

    public Quest(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    public String getCompletionIcon() {
        if (isCompleted) {
            return "[X]";
        } else {
            return "[ ]";
        }
    }

    public String getDescription() {
        return description;
    }

    public void markComplete() {
        if (isCompleted) {
            System.out.println("Quest is already complete!\n" + getCompletionIcon() + " " + description);
        } else {
            isCompleted = true;
            System.out.println("Quest Complete! Hooray!\n" + getCompletionIcon() + " " + description);
        }
    }

    public void markIncomplete() {
        if (isCompleted) {
            isCompleted = false;
            System.out.println("Quest Incomplete, the realm is in mortal danger!\n" + getCompletionIcon() + " " + description);
        } else {
            System.out.println("Quest is already incomplete...\n" + getCompletionIcon() + " " + description);
        }

    }
}
