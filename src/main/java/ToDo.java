public class ToDo extends Task{

    private ToDo(String description) {
        super(description);
    }

    /**
     * Factory method to create a ToDo task based on commands.
     * @param commands string array of commands
     * @return a ToDo object based on commands.
     */
    public static ToDo create(String[] commands) {
        if (commands.length < 2) {
            throw new IncompleteCommandException(String.format("Hrrmmm. Not enough arguments, " +
                    "%s has. Hmm", "todo"), null);
        }
        String toDoDescription = buildDescription(commands, 1, commands.length - 1);
        return new ToDo(toDoDescription);
    }

    public static ToDo create(String description, String marked) {
        ToDo newTask = new ToDo(description);
        if (marked.equals("1")) {
            newTask.markSilent();
        }
        return newTask;
    }

    /**
     * Concatenates the strings from [start, end] indices of commands array to form
     * required description of the task.
     * @param commands array of commands split by regex patterns like " "
     * @param start start index of commands array (inclusive)
     * @param end end index of commands array (inclusive)
     * @return a string description of a task
     */
    public static String buildDescription(String[] commands, int start, int end) {
        String description = "";
        for (int i = start; i < end; i++) {
            description += commands[i] + " ";
        }
        return description + commands[end];
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
