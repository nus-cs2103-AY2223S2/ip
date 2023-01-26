public class Deadline extends Task {
    private String end;
    public Deadline (String Name, String end) {
        super(Name);
        this.end = end;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + "(by: " + this.end + ")";
    }
}
