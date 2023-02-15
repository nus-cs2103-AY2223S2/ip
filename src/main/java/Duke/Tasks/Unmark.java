package duke.Tasks;

/**
 * Represents the class Unmark
 */
public class Unmark extends Task {

    int index;

    /**
     * The constructor of Unmark
     * @param index the index of the task
     */
    public Unmark(int index, int... nums) {
        super(false, "Undone");
        this.index = index;
    }

    /**
     * Override the run method and run the Unmark
     * @param table the task table
     * @param monitor the monitor
     */
    @Override
    public String run(TaskTable table, Monitor monitor, Disk disk) {
        table.unmark(index);
        monitor.displayUnmark(table, index);
        disk.write(table.getTable());
        String message = "    ____________________________________________________________\n" +
                "    Nice! I've marked this task as done:\n    " +
                table.get(index) + "\n" +
                "    ____________________________________________________________\n";
        return message;
    }
}
