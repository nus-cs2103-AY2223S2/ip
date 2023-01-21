import java.util.Arrays;

public class Deadline extends Task {

    private String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Factory method to create a Deadline task based on commands.
     * @param input command input
     * @return a Deadline task object based on command input
     */
    public static Deadline create(String input) {
        String[] splitCommands = input.trim().split(" ");
        splitCommands = StringUtils.removeWhiteSpace(splitCommands);
        int deadlineIndex = StringUtils.searchString(splitCommands, "deadline");
        int byIndex = StringUtils.searchString(splitCommands,"/by");
        if (byIndex == -1) {
            throw new IncompleteCommandException("Incomplete arguments for command deadline, I have found", null);
        }
        if (splitCommands[splitCommands.length - 1].equals("/by")) {
            throw new IncompleteCommandException("Deadline entered, you have not.", null);
        }
        if (deadlineIndex + 1 == byIndex) {
            throw new IncompleteCommandException("No Task Description, You have entered", null);
        }
        String description = StringUtils.joinString(splitCommands, deadlineIndex + 1, byIndex - 1);
        String deadline = StringUtils.joinString(splitCommands, byIndex + 1, splitCommands.length - 1);
        return new Deadline(description, deadline);
    }

    public static Deadline create(String description, String deadline, String marked) {
        Deadline task = new Deadline(description, deadline);
        if (marked.equals("1")) {
            task.markSilent();
        }
        return task;
    }

    /**
     * Checks the status of the task and logs to stdout.
     */
    @Override
    public void mark() {
        super.mark();
        System.out.println(String.format(" [%s][%s] %s (by: %s)",
                this.getTaskType(),
                this.getStatusIcon(),
                this.description,
                this.deadline));
    }

    /**
     * Unchecks the status of the task and logs to stdout.
     */
    @Override
    public void unmark() {
        super.unmark();
        System.out.println(String.format(" [%s][%s] %s (by: %s)",
                this.getTaskType(),
                this.getStatusIcon(),
                this.description,
                this.deadline));
    }

    /**
     * Method for child classes to return their type.
     * @return string type of task
     */
    @Override
    public String getTaskType() {
        return "D";
    }

    /**
     * String representation of the Deadline Task.
     * @return string representation of the Deadline Task.
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s (by: %s)",
                this.getTaskType(),
                this.getStatusIcon(),
                this.description,
                this.deadline);
    }

    @Override
    public String writeTask() {
        return String.format("%s %d %s by %s",
                this.getTaskType(),
                super.isCompleted() ? 1 : 0,
                this.description,
                this.deadline);
    }
}
