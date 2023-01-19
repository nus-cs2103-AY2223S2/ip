package Tasks;

import Tasks.Task;

public class Todo extends Task {
    private final String TASK_SIGN = "[T]";

    public Todo(String input) {
        super(input);
    }

    @Override
    public String getRepresentation(){
        return TASK_SIGN + this.getStatusIcon() + " " + this.getMessage();
    }
}
