package duke.tasks;

/**
 * A task which lasts for a time period, with a start time and end time.
 */
public class Event extends Task {

    /**
     * Constructs a new Event.
     * @param keyword The keyword command to create a new Event task.
     * @param message The description of the task.
     * @param isComplete The completion status of the task.
     */
    public Event(String keyword, String message, Boolean isComplete) {
        super(keyword, message, isComplete);
    }
    @Override
    public String toString() {
        String[] helperArray = this.taskDescription.split("/from");
        String firstPart = helperArray[0];
        String[] helperArray2 = helperArray[1].split("/to");
        String secondPart = helperArray2[0];
        String thirdPart = helperArray2[1];
        return this.isComplete ? "[E]" + "[x] " + firstPart + "(from:" + secondPart + "to:" + thirdPart + ")"
                              : "[E]" + "[ ] " + firstPart + "(from:" + secondPart + "to:" + thirdPart + ")";
    }
}
