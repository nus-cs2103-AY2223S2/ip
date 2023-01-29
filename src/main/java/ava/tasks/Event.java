package Ava.Tasks;
import Ava.Exceptions.DateTimeNotParsed;

public class Event extends Task {
    public static final String TASK_SIGN = "[E]";
    private String from;
    private String to;
    private TimeParser fromTp;
    private TimeParser toTp;

    public Event(String message, String from, String to) throws DateTimeNotParsed {
        super(message);
        this.from = from;
        this.to = to;
        this.fromTp = new TimeParser(this.from);
        this.toTp = new TimeParser(this.to);
    }

    @Override
    public String getMessage(){
        return this.message + " (FROM: " + this.fromTp + " TO: " + this.toTp + ")";
    }

    @Override
    public String getStorageFormat() {
        return TASK_SIGN + "," +this.isMarked()+","+this.message+"," +this.to + "," + this.from;
    }

    @Override
    public String getRepresentation(){
        return TASK_SIGN + this.getStatusIcon() + " " + this.getMessage();
    }
}
