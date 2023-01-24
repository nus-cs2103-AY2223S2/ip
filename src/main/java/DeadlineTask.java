import java.util.Objects;

public class DeadlineTask extends Task {

    private static final long serialVersionUID = 7701406742949264888L;

    protected String by;

    public DeadlineTask(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), by);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof DeadlineTask)) {
            return false;
        }
        DeadlineTask task = (DeadlineTask) obj;
        return super.equals(obj) && Objects.equals(by, task.by);
    }
}
