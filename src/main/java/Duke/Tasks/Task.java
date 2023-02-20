package Duke.Tasks;

import Duke.Exceptions.CommandNotFoundException;

public class Task {
    protected String description;
    protected boolean isComplete;

    public Task(String description) {
        this.description = description;
        this.isComplete = false;
    }

    public boolean noDescription() {
        return this.description == null || this.description.trim().isEmpty();
    }

    public boolean isMatch(String keywords) {
        return this.description.contains(keywords);
    }

    public static Task strToTask(String strTask) throws CommandNotFoundException {
        assert strTask != null;
        Task result;
        String[] strings = strTask.split(" \\| ");
        if (strTask.startsWith("T")) {
            result = new Todo(strings[2]);
        } else if (strTask.startsWith("D")) {
            result = new Deadline(strings[2], strings[3]);
        } else if (strTask.startsWith("E")) {
            String[] separatedBy = strings[3].split("-");
            result = new Event(strings[2], separatedBy[0], separatedBy[1]);
        } else {
            throw new CommandNotFoundException("Invalid command");
        }
        try {
            if (strings[1].equals("X")) {
                result.markDone();
            }
            return result;
        } catch (Exception e) {
            throw new CommandNotFoundException("Invalid command");
        }
    }

    /**
     * Mark the task as completed.
     */
    public void markDone() {
        this.isComplete = true;
    }

    /**
     * Mark the task as incomplete.
     */
    public void markNotDone() {
        this.isComplete = false;
    }

    /**
     * Get the status of the task, whether is it completed?
     * @return If done return "X", else not done return " ".
     */
    public String getTaskStatus() {
        return isComplete ? "X" : " ";
    }

    /**
     * Print the task into the specific file.
     *
     * @return Return the information of the task.
     */
    public String printTask() {
        return String.format("NA | %d | %s ", isComplete ? 1 : 0, description);
    }

    @Override
    public String toString() {
        return "[" + this.getTaskStatus() + "] " + this.description;
    }
}
