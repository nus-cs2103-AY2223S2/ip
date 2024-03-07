package duke.Tasks;

/**
 * The class of deletion
 */
public class Delete extends Task{

    int i;

    /**
     * Delete the task from the table
     * @param i the index of the task
     */
    public Delete(int i) {
        super(false, "delete");
        this.i = i;
    }

    /**
     * Override the Run method and execute the deletion
     * @param table the task table
     * @param monitor the monitor
     */
    @Override
    public String run(TaskTable table, Monitor monitor, Disk disk) {
        Task removedJob = table.delete(i);
        monitor.displayDelete(table, removedJob);
        disk.write(table.getTable());
        String message = "    ____________________________________________________________\n" +
                "     Noted. I've removed this task:\n" +
                "       " + removedJob + "\n" +
                "     Now you have " + table.size() +  " task(s) in the list.\n" +
                "    ____________________________________________________________\n";
        return message;
    }
}
