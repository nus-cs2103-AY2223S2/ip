package Ava.Tasks;

public class Todo extends Task {
    public static final String TASK_SIGN = "[T]";

    public Todo(String input) {
        super(input);
    }

    @Override
    public String getRepresentation(){
        return TASK_SIGN + this.getStatusIcon() + " " + this.getMessage();
    }

    @Override
    public String getStorageFormat() {
        return TASK_SIGN + "," +this.isMarked()+","+this.message;
    }
}
