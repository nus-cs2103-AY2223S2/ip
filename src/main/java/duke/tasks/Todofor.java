package duke.tasks;

/**
 * Inherits abstract class Task. Characterised by description only.
 *
 * @author jengoc415
 */
public class Todofor extends Task {
    private String duration;

    /**
     * Constructor for task type: within
     *
     * @param instruction full instruction keyed in by user
     */
    public Todofor(String instruction) {
        super(instruction);
        this.modifiedInstr = instruction.substring(8);
        String[] modInstrSplit = modifiedInstr.split(" /for ");
        this.description = modInstrSplit[0];
        this.duration = modInstrSplit[1];
    }

    @Override
    public String toString() {
        return String.format("[T]%s %s (needs %s)",
                super.toString(), description, duration);
    }
}
