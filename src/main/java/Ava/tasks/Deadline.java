package Ava.tasks;


import Ava.exceptions.DateTimeNotParsed;

/**
 * Deadline class extends Task
 */
public class Deadline extends Task {
    public static final String TASK_SIGN = "[D]";
    private String by;
    private TimeParser tpBy;

    /**
     * Contructor for Deadline message
     * @param message String message
     * @param by Date and Time String
     * @throws DateTimeNotParsed throw when TimeParse is unable to parse by input
     */
    public Deadline(String message, String by) throws DateTimeNotParsed {
        super(message);
        this.by = by;
        this.tpBy = new TimeParser(this.by);
        super.deadline = tpBy; //Used for comparing
    }

    /**
     * @return message + formatted Date & Time
     */
    @Override
    public String getMessage(){
        return this.message + " (BY: " + this.tpBy + ")";
    }

    /**
     * @return task_sign + mark/unmark sign + message
     */
    @Override
    public String getRepresentation(){
        return TASK_SIGN + this.getStatusIcon() + " " + this.getMessage();
    }

    /**
     * @return storage format of the task
     */
    @Override
    public String getStorageFormat() {
        return TASK_SIGN + "," +this.isMarked()+","+this.message+"," +this.by;
    }


}
