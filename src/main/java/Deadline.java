public class Deadline extends Task{
    private String endDate;

    public Deadline(String name, String endDate) {
        super(name);
        this.endDate = endDate;
    }

    public  String toText() {
        return "D" + "|" + super.toText() + "|" + endDate;
    };
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + endDate + ")";
    }
}
