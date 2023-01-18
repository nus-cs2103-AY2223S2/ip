package types;

public class Deadline extends Task {
    private final String before;

    private Deadline(String n, String d) {
        super(n, "D");
        before = d;
    }

    public static Deadline create(String n, String d) {
        return new Deadline(n, d);
    }

    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(), before);
    }
}
