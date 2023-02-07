package duke;

/**
 * A subclass of task indicating the type of task
 */
class ToDo extends Task {
    final String icon = "[T]";

    /**
     * @param details details of the task
     */
    public ToDo(String details) {
        super(details);

    }

    /**
     * @return a String type indicating the type of the task
     */
    @Override
    public String toString() {
        return icon + super.toString();
    }
}
