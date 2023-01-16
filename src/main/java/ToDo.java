public class ToDo extends Task {

    protected String by;

    public ToDo(String description) {
        super(description);
        Task.actions += 1;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString() ;
    }
}
