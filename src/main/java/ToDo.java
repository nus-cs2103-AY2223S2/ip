public class ToDo extends Task {

    public ToDo(String name) {
        super(name);
    }

    @Override
    protected String getTaskType() {
        return "T";
    }
}
