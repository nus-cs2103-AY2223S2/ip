package jeno.task;

/**
 * Class for ToDo task
 */
public class ToDo extends Task {
    public ToDo(String name) {
        super(name);
    }

    /**
     * Converts todo task to its task log format to be saved in task log file
     * @return String representing todo task in task log format
     */
    @Override
    public String toLog() {
        return "T" + super.toLog() + "\n";
    }

    /**
     * Converts todo task to string format which is echoed to user
     * @return String representation of todo task
     */
    @Override
    public String toString() {
        return ("[T]" + super.toString());
    }
}
