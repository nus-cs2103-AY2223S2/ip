public class ToDo extends Task{

    public ToDo(String description) {
        super(description);
    }

    /**
     * Checks the status of the task and logs to stdout.
     */
    @Override
    public void mark() {
        super.mark();
        System.out.println(String.format(" [%s][%s] %s",
                this.getTaskType(),
                this.getStatusIcon(),
                this.description));
    }

    /**
     * Unchecks the status of the task and logs to stdout.
     */
    @Override
    public void unmark() {
        super.unmark();
        System.out.println(String.format(" [%s][%s] %s",
                this.getTaskType(),
                this.getStatusIcon(),
                this.description));
    }

    /**
     * Method for child classes to return their type.
     * @return string type of task
     */
    @Override
    public String getTaskType() {
        return "T";
    }

    /**
     * String representation of the ToDo Task.
     * @return string representation of the ToDo Task.
     */
    @Override
    public String toString() {
        return String.format(String.format("[%s][%s] %s",
                this.getTaskType(),
                this.getStatusIcon(),
                this.description));
    }
}
