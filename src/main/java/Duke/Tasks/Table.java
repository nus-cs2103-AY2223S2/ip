package duke.Tasks;

/**
 * Represents task table class
 */
public class Table extends Task {

    /**
     * The constructor of task table
     */
    public Table() {
        super(false, "list");
    }

    /**
     * Override the run method and show the table
     * @param table the task table
     * @param monitor the monitor
     */
    @Override
    public String run(TaskTable table, Monitor monitor, Disk disk) {

        monitor.showTable(table);
        String message;
        if (table.size() == 0) {
            message = "    ____________________________________________________________\n" +
                    "    Yo there is no task in your list, go get some rest!\n";
        } else {
            message = "    ____________________________________________________________\n" +
                    "    Here are the task(s) in your list:\n";
        }
        for (int i = 0; i < table.size(); i ++) {
            message = message.concat("    " + (i + 1) + "." + "    " + table.get(i) + "\n");
        }
        message = message.concat("    ____________________________________________________________\n");
        return message;

    }
}
