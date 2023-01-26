public class Deadline extends Task{

    protected String by;
    Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toSaveFormat() {
        return "D||" + super.toSaveFormat() + "||" + this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    public static Deadline fromSaveFormat(String savedData) {
        String[] inputs = savedData.split("\\|\\|");
        Deadline generatedDeadline = new Deadline(inputs[2], inputs[3]);
        if (inputs[1].equals("1")) {
            generatedDeadline.setCompleted(true);
        }
        return generatedDeadline;
    }
}
