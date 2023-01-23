public class ToDo extends Task {
    protected String by;
    boolean isDone;

    public ToDo(String description, Boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "\t[T]" + super.toString();
    }

    public String saveFormat(){
        return String.format("T | %s", super.saveFormat());
    }

}
