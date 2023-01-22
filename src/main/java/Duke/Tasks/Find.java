package Duke.Tasks;

/**
 * Represents the find class
 */
public class Find extends Task {
    protected String keyword;

    /**
     * The constructor for Find class
     *
     * @param keyword
     * @param table
     */
    public Find(String keyword, TaskTable table) {
        super(false, "find");
        this.keyword = keyword;
    }

    /**
     * Override the run method and run the find function
      * @param table the task table
     * @param monitor the monitor
     * @param disk the disk
     */
    @Override
    public void run(TaskTable table, Monitor monitor, Disk disk) {
        monitor.displayFind(table, keyword);
    }

}
