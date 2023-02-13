package duke;

public class Task implements Comparable<Task> {

    enum PriorityLevel {
        LOW,
        MEDIUM,
        HIGH
    }
    private String name;
    private boolean isCompleted;
    private PriorityLevel priority;

    /**
     * Constructor for a Task instance.
     *
     * @param name the name of this Task as a simple description.
     */
    public Task(String name) {
        this.name = name;
        this.isCompleted = false;
        this.priority = PriorityLevel.LOW;
    }

    @Override
    public int compareTo(Task other) {
        int currentTask = getPriorityAsInt();
        int otherTask = other.getPriorityAsInt();
        if (otherTask > currentTask) {
            return 1;
        } else if (otherTask < currentTask) {
            return -1;
        } else {
            return 0;
        }
    }

    /**
     * Marks the task as completed.
     * It does not matter whether the task has previously been marked as completed.
     */
    public void makeCompleted() {
        this.isCompleted = true;
    }

    /**
     * Unmarks the task as incomplete.
     */
    public void makeIncomplete() {
        this.isCompleted = false;
    }

    public String increasePriority() {
        String result = "";
        if (this.priority == PriorityLevel.LOW) {
            this.priority = PriorityLevel.MEDIUM;
            result += "Task is now at priority level Medium\n";
        } else if (this.priority == PriorityLevel.MEDIUM) {
            this.priority = PriorityLevel.HIGH;
            result += "Task is now at priority level High\n";
        } else {
            result += "Task is of highest priority level already!\n";
        }
        return result;
    }

    public String decreasePriority() {
        String result = "";
        if (this.priority == PriorityLevel.HIGH) {
            this.priority = PriorityLevel.MEDIUM;
            result += "Task is now at priority level Medium\n";
        } else if (this.priority == PriorityLevel.MEDIUM) {
            this.priority = PriorityLevel.LOW;
            result += "Task is now at priority level Low\n";
        } else {
            result += "Task is of lowest priority level already!\n";
        }
        return result;
    }

    private int getPriorityAsInt() {
        if (this.priority == PriorityLevel.LOW) {
            return 1;
        } else if (this.priority == PriorityLevel.MEDIUM) {
            return 2;
        } else {
            return 3;
        }
    }
    /**
     * Returns the string representation of the task.
     * A [ ] indicates an uncompleted task, while a [X] indicates a completed task.
     *
     * @return the desired string representation of the task with its description.
     */
    @Override
    public String toString() {
        String completedString = "";

        if (this.isCompleted) {
            completedString += "[X] ";
        } else {
            completedString += "[  ] ";
        }
        completedString += "[Priority Level: " + priority + "] ";
        completedString += this.name;
        return completedString;
    }

    /**
     * Returns the string representation of the task.
     * A [ ] indicates an uncompleted task, while a [X] indicates a completed task.
     *
     * @return the desired string representation of the task with its description.
     */
    public String parse() {
        String completedString = "";
        if (this.isCompleted) {
            completedString += "[X] ";
        } else {
            completedString += "[  ] ";
        }

        completedString += this.name;
        return completedString;
    }

    public String addOn() {
        if (this.priority == PriorityLevel.LOW) {
            return "L";
        } else if (this.priority == PriorityLevel.MEDIUM) {
            return "M";
        } else {
            return "H";
        }
    }

    public boolean contains(String keyword) {
        return name.contains(keyword);
    }
}
