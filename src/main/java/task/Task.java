package task;

/**
 * Represents an entry in the to-do list.
 */
public class Task {
    private String instruction;
    private boolean isCompleted;

    /**
     * Creates a new task without any deadlines
     * @param instruction name user gives
     */
    public Task(String instruction) {
        this.instruction = instruction;
        this.isCompleted = false;
    }

    /**
     * Marks entry as completed and prompts user if action is redundant.
     */
    public String setComplete() {
        if (this.isCompleted) {
            return "Mission is already completed.";
        }
        this.isCompleted = true;
        return "Mission Completed!\n" + this;
    }


    /**
     * Marks entry as incomplete and prompts user if action is redundant.
     */
    public String setIncomplete() {
        if (!this.isCompleted) {
            return "Mission is originally incomplete.";
        }
        this.isCompleted = false;
        return "Mission Re-initialised\n" + this;
    }

    /**
     * Checks if the current task matches the user's request
     * @param s keyword searching for
     * @return boolean value indicating if this task should be printed
     */
    public boolean contains(String s) {
        return instruction.contains(s);
    }
    @Override
    public String toString() {
        if (isCompleted) {
            return "[X] " + instruction;
        }
        return "[ ] " + instruction;
    }
}
