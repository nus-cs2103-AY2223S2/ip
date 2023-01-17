public enum TaskType {
    TODO("todo", new DukeException("The description is compulsory.")),
    EVENT("event", new DukeException("The description and date is compulsory.")),
    DEADLINE("deadline", new DukeException("The description and date is compulsory."));
    private final String type;
    private final DukeException err;

    TaskType(String type, DukeException err) {
        this.type = type;
        this.err = err;
    }

    public String getType() {
        return this.type;
    }

    public DukeException getErr() {
        return err;
    }
}
