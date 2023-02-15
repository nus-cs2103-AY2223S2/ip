package duke.Tasks;

/**
 * Represents the Mark class
 */
public class Mark extends Task {
    int index;

    /**
     * The constructor for Mark class
     * @param index the index of the task
     */
    public Mark(int index) {
        super(true, "Done");
        this.index = index;
    }

    /**
     * Override the run method and execute the marking process
     * @param table the task table
     * @param monitor the monitor
     */
    @Override
    public String run(TaskTable table, Monitor monitor, Disk disk) {
        table.mark(index);
        monitor.displayMark(table, index);
        disk.write(table.getTable());
        String message = "    ____________________________________________________________\n" +
                "    Nice! I've marked this task as done:\n    " +
                table.get(index) + "\n" +
                "    ____________________________________________________________\n";
        return message;
    }
}
