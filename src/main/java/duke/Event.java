package duke;

/**
 * A task which lasts for a time period, with a start time and end time.
 */
class Event extends Task {

    /**
     * Constructs a new Event.
     * @param keyword The keyword command to create a new Event task.
     * @param message The description of the task.
     * @param completed The completion status of the task.
     */
    public Event(String keyword, String message, Boolean completed) {
        super(keyword, message, completed);
    }

    @Override
    public String provideDetails() {
        String[] helperArray = this.description.split("/from");
        String firstPart = helperArray[0];
        String[] helperArray2 = helperArray[1].split("/to");
        String secondPart = helperArray2[0];
        String thirdPart = helperArray2[1];

        return this.completed ? "[E]" + "[x] " + firstPart + "(from:" + secondPart + "to:" + thirdPart + ")"
                              : "[E]" + "[ ] " + firstPart + "(from:" + secondPart + "to:" + thirdPart + ")";

    }

}
