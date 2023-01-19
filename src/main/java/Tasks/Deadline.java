package Tasks;

import Tasks.Task;

public class Deadline extends Task {
    private final String TASK_SIGN = "[D]";
    private String by;

    public Deadline(String message, String by){
        super(message);
        this.by = by;
    }

    @Override
    public String getMessage(){
        return this.message + " (by: " + this.by + ")";
    }

    @Override
    public String getRepresentation(){
        return TASK_SIGN + this.getStatusIcon() + " " + this.getMessage();
    }
}
