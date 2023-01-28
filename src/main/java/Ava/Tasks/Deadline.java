package Ava.Tasks;

import Ava.Exceptions.DateTimeNotParsed;


public class Deadline extends Task {
    public static final String TASK_SIGN = "[D]";
    private String by;
    private TimeParser tpBy;

    public Deadline(String message, String by) throws DateTimeNotParsed {
        super(message);
        this.by = by;
        this.tpBy = new TimeParser(this.by);
    }

    @Override
    public String getMessage(){
        return this.message + " (BY: " + this.tpBy + ")";
    }


    @Override
    public String getRepresentation(){
        return TASK_SIGN + this.getStatusIcon() + " " + this.getMessage();
    }

    @Override
    public String getStorageFormat() {
        return TASK_SIGN + "," +this.isMarked()+","+this.message+"," +this.by;
    }
}
