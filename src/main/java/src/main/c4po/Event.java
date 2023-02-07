package src.main.c4po;

public class Event extends Task{
    protected String start;
    protected String end;

    /**
     * Instantiates a new instance of Event task, specifying description, start time and end time as strings
     * @param description String of task description
     * @param start String of start time
     * @param end String of end time
     */
    public Event(String description, String start, String end, Integer priority) {
        super(description, false, priority);
        this.start = start;
        this.end = end;
    }

    /**
     * Instantiates a new instance of Event task, specifying description, start time and end time as strings
     * @param description String of task description
     * @param start String of start time
     * @param end String of end time
     * @param isDone boolean of whether task is done
     */
    public Event(String description, String start, String end, boolean isDone, Integer priority) {
        super(description, isDone , priority);
        this.start = start;
        this.end = end;
    }




    @Override
    protected String getTaskFileFormat() {
        return "E" + " | " + super.getTaskFileFormat() + " | " + start + " | " + end;
    }

    /**
     * Returns an inline String that details the task type (Event) and the
     * task details
     * @return String
     */
    @Override
    public String getTaskInline() {
        return "[E]" + super.getTaskInline();
    }

    /**
     * Returns an inline String that details the task type (Event) and the
     * task details and the list index
     * @return String
     */
    @Override
    public String getTaskInline(Integer index) {
        return index.toString() + ". [E]" + super.getTaskInline() + " (from: " + start + " to: " + end + ")";
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " + this.start + " to " + this.end + ")";
    }
}
