package duke.Tasks;

/**
 * Represents the Todo class
 */
public class Todo extends Task {

    /**
     * The constructor for Todo
     * @param desc the description
     * @param done whether the task is done
     */
    public Todo(String desc, boolean done) {
        super(done, desc);
    }

    /**
     * Override the mark to indicate the task as done
     * @return Todo
     */
    @Override
    public Todo mark() {
        super.mark();
        return this;
    }

    /**
     * Override the unmark to change the task to undone
     * @return Todo
     */
    @Override
    public Todo unmark() {
        super.unmark();
        return this;
    }

    /**
     * Override the run method to run the Todo
     * @param table the task table
     * @param monitor the monitor
     */
    @Override
    public String run(TaskTable table, Monitor monitor, Disk disk)  {
        table.add(this);
        monitor.displayAdd(table, table.size() - 1);
        disk.write(table.getTable());
        String message = "    ____________________________________________________________\n" +
                "     Got it. I've added this task:\n" +
                "       " + this.toString() + "\n" +
                "     Now you have " + table.size() +  " task(s) in the list.\n" +
                "    ____________________________________________________________\n";
        return message;
    }

    /**
     * Override the toString method and return String form of Todo
     * @return String
     */
    @Override
    public String toString() {
        return "[T]"  + super.toString();
    }

    /**
     * Override the reformat method and convert to standard output
     * @return String
     */
    @Override
    public String reformat() {
        String d;
        if (isDone) {
            d = "1";
        } else {
            d = "0";
        }
        return "T | " + d + " | " + this.desc;
    }
}
