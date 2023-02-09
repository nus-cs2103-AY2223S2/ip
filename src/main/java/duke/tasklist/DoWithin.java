package duke.tasklist;

public class DoWithin extends Task {
    private String from;
    private String to;

    public DoWithin(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[W]" + super.toString() + "(between:" + from + " and:" + to + ")";
    }
    @Override
    public String toSave() {
        return "[W]" + super.toString() + "(between:" + from + " and:" + to + ")";
    }
}
