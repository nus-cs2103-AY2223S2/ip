package hachi.tasks;

/**
 * Encapsulates a Task with a starting date and an ending date..
 */
public class Event extends Task {
    private String from;
    private String to;

    /**
     * Event constructor.
     *
     * @param input The description of the task.
     * @param from  The starting date of the task.
     * @param to    The ending date of the task.
     */
    public Event(String input, String from, String to) {
        super(input);
        this.from = from;
        this.to = to;
    }

    public boolean checkInput(Event taskOne, Event taskTwo) {
        return taskOne.input.equals(taskTwo.input);
    }

    public boolean checkFrom(Event taskOne, Event taskTwo) {
        return taskOne.from.equals(taskTwo.from);
    }

    public boolean checkTo(Event taskOne, Event taskTwo) {
        return taskOne.to.equals(taskTwo.to);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Event) {
            Event task = (Event) o;
            if (checkInput(task, this) || checkFrom(task, this) || checkTo(task, this)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * Returns the string representation of the Event object with
     * specified description and dates.
     *
     * @return String representation of the Event object
     */
    public String toString() {
        return "   [E]" + super.toString() + " (from: " + from + " (to: " + to + ")";
    }

}
