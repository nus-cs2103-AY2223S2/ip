public class Deadline extends Task {
    private String by;

    public Deadline(String content, String by) {
        super(content);
        this.by = by;
    }

    public static Deadline create(String content) {
        String[] splitted = content.split("/by");
        if (splitted.length <= 1) {
            System.out.println("Invalid input for deadline creation.");
            return null;
        }
        return new Deadline(splitted[0].strip(), splitted[1].strip());
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.by);
    }
}
