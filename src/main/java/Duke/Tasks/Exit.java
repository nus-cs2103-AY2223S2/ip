package Duke.Tasks;

/**
 * Represents the Exit class
 */
public class Exit extends Task {
    /**
     * The constructor for Exit class
     */
    public Exit() {
        super(false, "exit");
        this.exited = true;
    }
}
