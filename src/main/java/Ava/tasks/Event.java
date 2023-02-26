package Ava.tasks;

import Ava.exceptions.DateTimeNotParsed;

/**
 * Event class extends Task
 */
public class Event extends Task {
    public static final String TASK_SIGN = "[E]";
    private String from;
    private String to;
    private TimeParser tpFrom;
    private TimeParser tpTo;

    /**
     * Contructor for Event message
     * @param message String message
     * @param from Date and Time String
     * @param to Date and Time String
     * @throws DateTimeNotParsed throw when TimeParse is unable to parse by input
     */
    public Event(String message, String from, String to) throws DateTimeNotParsed {
        super(message);
        this.from = from;
        this.to = to;
        this.tpFrom = new TimeParser(this.from);
        this.tpTo = new TimeParser(this.to);
        super.deadline = tpTo; //Used for comparing
    }

    /**
     * @return message + formatted Date & Time
     */
    @Override
    public String getMessage(){
        return this.message + " (FROM: " + this.tpFrom + " TO: " + this.tpTo + ")";
    }

    /**
     * @return storage format of the task
     */
    @Override
    public String getStorageFormat() {
        return TASK_SIGN + "," +this.isMarked()+","+this.message+"," +this.to + "," + this.from;
    }

    /**
     * @return task_sign + mark/unmark sign + message
     */
    @Override
    public String getRepresentation(){
        return TASK_SIGN + this.getStatusIcon() + " " + this.getMessage();
    }
}
