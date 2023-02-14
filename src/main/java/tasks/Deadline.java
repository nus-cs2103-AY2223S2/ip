package tasks;

public class Deadline extends Task {
    protected String by;
    String description;

    public Deadline(String instruction) {
        super(instruction);
        this.modifiedInstr = instruction.substring(9);
        this.description = modifiedInstr.split("/")[0];
        this.by = modifiedInstr.split("/")[1].substring(3);
    }

    @Override
    public String toString() {
        return String.format("[D]%s %s (by: %s)", super.toString(), description, by);
    }
}
