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
    public String run(TaskTable table, Monitor monitor, Disk disk) {
        monitor.displayFind(table, keyword);
        String message = "    ____________________________________________________________\n" +
                "     Here are the matching tasks in your list:\n";
        for (int i = 0; i < table.size(); i++) {
            if(table.getTable().get(i).showDesc().contains(keyword)) {
                message = message.concat("    " + (i + 1) + "." + "    " + table.get(i) + "\n");
            }
        }
        message = message.concat("    ____________________________________________________________\n");
        return message;
    }

}