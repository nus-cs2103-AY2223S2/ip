public class Deadline extends Task{

    String taskDescription;
    String deadline;

    public Deadline(String taskString) {
        super (taskString.substring(9, taskString.indexOf("/") - 1)
                + " (by: " + taskString.substring(taskString.indexOf("/") + 4) + ")");

        taskDescription = taskString.substring(9, taskString.indexOf("/") - 1);
        deadline = taskString.substring(taskString.indexOf("/") + 4);
    }
    @Override
    public String getTask() {
        return this.taskDescription;
    }

    public String getDeadline() {
        return this.deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString();
    }
}
