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
        //First part is  the description of the task
        String description = separateText[0];
        //Second part is the date in which the task must be done after.
        String dateToDoAfter = separateText[1];

        return this.isComplete ? String.format("[DA][X] %s (after:%s)", description, dateToDoAfter)
                               : String.format("[DA][] %s (after:%s)", description, dateToDoAfter);
    }
}





