package duke.tasks;

public class Event extends Task {
    private String start;
    private String end;

    public Event(String instruction) {
        super(instruction);
        this.modifiedInstr = instruction.substring(6);
        this.description = modifiedInstr.split(" /")[0];
        this.start = modifiedInstr.split(" /")[1].substring(5);
        this.end = modifiedInstr.split(" /")[2].substring(3);
    }

    @Override
    public String toString() {
        return String.format("[E]%s %s (from: %s to: %s)",
                super.toString(),
                description,
                this.start,
                this.end
        );
    }
}
