package task;

import duke.Ui;

/**
 * Base class for all types of tasks.
 */
public abstract class Task {

    private final String taskName;

    private Boolean isTaskDone;

    private PriorityLevel priorityLevel;

    private final Boolean isTaskDoneDefault = false;

    private final PriorityLevel defaultPriorityLevel = PriorityLevel.LOW;

    private final String dateFormatA = "dd/MM/yyyy";

    private final String dateFormatB = "dd-MM-yyyy";

    /**
     * Constructor for Task.
     *
     * @param taskName Name of task.
     * @param isTaskDone Status of task.
     * @param priorityLevel Priority level of task.
     */
    protected Task(String taskName, Boolean isTaskDone, PriorityLevel priorityLevel) {
        this.taskName = taskName;
        this.isTaskDone = isTaskDone;
        this.priorityLevel = priorityLevel;
    }

    /**
     * Constructor for Task with default value for isTaskDone
     *
     * @param taskName Name of task.
     */
    protected Task(String taskName) {
        this.taskName = taskName;
        this.isTaskDone = this.isTaskDoneDefault;
        this.priorityLevel = this.defaultPriorityLevel;
    }

    /**
     * Method to write all task into file
     *
     * @return String denoting the new task.
     */
    public abstract String writeToFile();

    /**
     * Mark task as done.
     *
     * @return Task that was marked done.
     */
    public Task markIsDone() {
        this.isTaskDone = true;
        return this;
    }

    /**
     * Mark task as not done.
     *
     * @return Task that was marked not done.
     */
    public Task markNotDone() {
        this.isTaskDone = false;
        return this;
    }

    /**
     * Return task status.
     *
     * @return True if task is done.
     */
    public Boolean isDone() {
        return this.isTaskDone;
    }

    @Override
    public String toString() {
        if (isTaskDone) {
            return "[X] " + this.taskName;
        }
        return "[ ] " + this.taskName;
    }

    public String setPriorityHigh() {
        this.priorityLevel = PriorityLevel.HIGH;
        return Ui.setToHighPriorityMessage(this);
    }

    public String setPriorityMid() {
        this.priorityLevel = PriorityLevel.MID;
        return Ui.setToMidPriorityMessage(this);
    }

    public String setPriorityLow() {
        this.priorityLevel = PriorityLevel.LOW;
        return Ui.setToLowPriorityMessage(this);
    }

    public String getName() {
        return this.taskName;
    }

    public String getTaskDetails() {
        return this.toString();
    }

    public String getDateFormatA() {
        return this.dateFormatA;
    }

    public String getDateFormatB() {
        return this.dateFormatB;
    }

    public PriorityLevel getPriority() {
        return this.priorityLevel;
    }
}
