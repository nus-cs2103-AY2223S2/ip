public class ToDo extends Task {

    public ToDo(String name) {
        super(name);
    }

    protected String getTaskType() {
        return "T";
    }
}
