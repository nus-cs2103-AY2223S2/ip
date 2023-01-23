package Duke.Tasks;

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
    public void run(TaskTable table, Monitor monitor, Disk disk)  {
        table.add(this);
        monitor.displayAdd(table, table.size() - 1);
        disk.write(table.getTable());
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
        if (done) {
            d = "1";
        } else {
            d = "0";
        }
        return "T | " + d + " | " + this.desc;
    }
}
