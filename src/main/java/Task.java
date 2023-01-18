public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * This method marks task as done.
     *
     * @return  void
     */
    public void markTask() {
        this.isDone = true;
        this.getTask();
    }

    /**
     * This method unmarks task as undone.
     *
     * @return  void
     */
    public void unmarkTask() {
        this.isDone = false;
        this.getTask();
    }

    /**
     * This method marks done task with X.
     *
     * @return  void
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * This method gets the name of the task type.
     *
     * @return  void
     */
    public char getTaskType() {
        return (this instanceof Todo
                ? 'T'
                : this instanceof Deadline
                ? 'D'
                : 'E');
    }

    /**
     * This method gets extra information of the task like its corresponding datetimes.
     *
     * @return  void
     */
    public String getExtraInfo() {
        return (this instanceof Deadline
                ? "(by:" + ((Deadline) this).deadline + ")"
                : this instanceof Event
                ? "(from:" + ((Event) this).startDatetime + "to:" + ((Event) this).endDatetime + ")"
                : "");
    }

    /**
     * This method prints the task type, status, description, and if relevant, its datetimes.
     *
     * @return  void
     */
    public void getTask() {
        System.out.println("[" + this.getTaskType() + "]"
                + "[" + this.getStatusIcon() + "] "
                + this.description
                + this.getExtraInfo());
    }
}
