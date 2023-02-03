package page.quest;

/**
 * Represents a quest, i.e. a task that the user must do.
 */
public class Quest {
    /** Description of the quest */
    private String description;

    /** Completion status of the quest */
    private boolean isCompleted;

    /**
     * Constructs a Quest with the given description, that is not completed.
     *
     * @param description Description of the Quest.
     */
    public Quest(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    /**
     * Returns a String representing the completion status of the quest.
     *
     * @return "[X]" if quest is complete, "[_]" otherwise.
     */
    private String getCompletionIcon() {
        if (isCompleted) {
            return "[X]";
        } else {
            return "[_]";
        }
    }

    /**
     * Marks the quest as complete.
     */
    public void markComplete() {
        isCompleted = true;
    }

    /**
     * Marks the quest as incomplete.
     */
    public void markIncomplete() {
        isCompleted = false;
    }

    /**
     * Returns the String representation of the quest.
     *
     * @return String representation of the quest.
     */
    @Override
    public String toString() {
        return getCompletionIcon() + " " + description;
    }
}
