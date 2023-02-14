package duke.tasks;

/**
 * A task that is to be done after a specific date.
 */
public class DoAfter extends Task {

    /**
     * Constructs a new DoAfter task.
     * @param keyword keyword command to create new doafter task
     * @param message description of the task
     * @param isComplete status of the task
     */
    public DoAfter(String keyword, String message, Boolean isComplete) {
        super(keyword, message, isComplete);
    }

    @Override
    public String toString() {
        String[] separateText = this.taskDescription.split("/after");

        return this.isComplete ? "[DA]" + "[x] " + separateText[0] + " (after: " + separateText[1] + ")"
                : "[DA]" + "[ ] " + separateText[0] + " (after: " + separateText[1] + ")";
    }
}





