package Tasks;

import Tasks.Task;

public class Event extends Task {
    private final String TASK_SIGN = "[E]";
    private String from;
    private String to;

    public Event(String message, String from, String to){
        super(message);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getMessage(){
        return this.message + " (from: " + this.from + " to: " + this.to + ")";
    }

    @Override
    public String getRepresentation(){
        return TASK_SIGN + this.getStatusIcon() + " " + this.getMessage();
    }
}
