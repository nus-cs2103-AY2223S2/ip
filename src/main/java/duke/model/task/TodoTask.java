package duke.model.task;

public class TodoTask extends Task {

    private static final long serialVersionUID = -2700942093958172810L;

    public TodoTask(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof TodoTask)) {
            return false;
        }
        return super.equals(obj);
    }
}
