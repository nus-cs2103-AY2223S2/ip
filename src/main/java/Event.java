import java.util.List;

class Event extends Task {
    private final String taskType;
    private final String eventStartTime;
    private final String eventEndTime;

    Event(int taskNumber, boolean taskStatus, String task,
         String eventStartTime, String eventEndTime, int totalNumOfTasks) {
        super(taskNumber, taskStatus, task, totalNumOfTasks);
        this.taskType = "[E]";
        this.eventStartTime = eventStartTime;
        this.eventEndTime = eventEndTime;
    }

    @Override
    public void markAsDone() {
        System.out.println("\t____________________________________________________________" +
                "\n\t Nice! I've marked this task as done:\n" +
                "\t  " + this.taskType + "[X]" + " " + super.task +
                " (from: " + this.eventStartTime + " to: "
                + this.eventEndTime + ")" +
                "\n\t____________________________________________________________");
    }

    @Override
    public void unmarkAsUndone() {
        System.out.println("\t____________________________________________________________" +
                "\n\t OK, I've marked this task as not done yet:\n" +
                "\t  " + this.taskType + "[ ]" + " " + super.task +
                " (from: " + this.eventStartTime + " to: "
                + this.eventEndTime +
                "\n\t____________________________________________________________");
    }

    public void printEventTask() {
        System.out.println("\t____________________________________________________________" +
                "\n\t Got it. I've added this task:" +
                "\n\t   [E]" + super.getTaskStatus() + " " + super.task +
                "(from: " + this.eventStartTime + " to: " + this.eventEndTime + ")" +
                "\n\t Now you have " + super.totalNumOfTasks + " tasks in the list." +
                "\n\t____________________________________________________________");
    }

    @Override
    public void printDelete(List<Task> allTasks) {
        int newTotalNumOfTasks = allTasks.size() - 1;
        System.out.println("\t____________________________________________________________" +
                "\n\t Noted. I've removed this task:" +
                "\n\t   " + this.taskType +
                super.getTaskStatus() + " " + super.task + " (from: " + this.eventStartTime +
                " to: " + this.eventEndTime + ")" + "\n\t Now you have " +
                newTotalNumOfTasks + " tasks in the list." +
                "\n\t____________________________________________________________");
    }

    @Override
    public String getTaskType() {
        return this.taskType;
    }

    @Override
    public String getEventStartTime() {
        return this.eventStartTime;
    }

    @Override
    public String getEventEndTime() {
        return this.eventEndTime;
    }
}
