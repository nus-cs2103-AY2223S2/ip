package task;

/**
 * Represents an entry in the to-do list.
 */
public class Task {
    private String instruction;
    private boolean status;

    public Task(String instruction) {
        this.instruction = instruction;
        this.status = false;
    }

    /**
     * Marks entry as completed and prompts user if action is redundant.
     */
    public String setComplete() {
        if (this.status) {
            return "Mission is already completed.";
        }
        this.status = true;
        return "Mission Completed!\n" + this;
    }


    /**
     * Marks entry as incomplete and prompts user if action is redundant.
     */
    public String setIncomplete() {
        if (!this.status) {
            return "Mission is originally incomplete.";
        }
        this.status = false;
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
        if (status) {
            return "[X] " + instruction;
        }
        return "[ ] " + instruction;
    }
}
