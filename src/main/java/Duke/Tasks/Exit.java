package duke.Tasks;

/**
 * Represents the Exit class
 */
public class Exit extends Task {
    /**
     * The constructor for Exit class
     */
    public Exit() {
        super(false, "exit");
        this.isExited = true;
    }

    @Override
    public String run(TaskTable table, Monitor monitor, Disk disk) {
        this.isExited = true;
        String message = "    ____________________________________________________________\n" +
                "     Come back with something better next time.\n" +
                "    ____________________________________________________________\n";
        return message;
    }
}
