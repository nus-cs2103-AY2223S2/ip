public class Deadline extends Task{

    private final String end;
    public Deadline(String name, String end) {
        super(name);
        this.end = end;
    }

    @Override
    public String toString() {
        return "   [D]" + super.toString() + " |by:" + end + "|\n";

    }
}
