public class Deadline extends Task{
    protected String deadline;

    public Deadline(String taskName, String deadline) {
        super(taskName);
        this.deadline = deadline;
    }

    public static void processDeadline(String command, TaskList lst) throws DukeException{
        String info = command.trim();
        if (info.isEmpty()) {
            throw new DukeException("deadline");
        }
        String[] details = info.split("/");
        if (details.length < 2) {
            throw new DukeException("timing");
        }
        String deadline = details[1].split(" ", 2)[1];
        Deadline d = new Deadline(details[0], deadline);
        lst.addTask(d);
        Duke.printLine();
        System.out.println("Got it! I've added: ");
        System.out.println(" " + d.toString());
        lst.printSize();
        Duke.printLine();
    }

    @Override
    public String toFile() {
        int completed = this.completed ? 1 : 0;
        return String.format("D | %d | %s | %s\n", completed, this.taskName, this.deadline);
    }

    public static Deadline toDeadlineFromFileStr(String taskNameData, String doneData, String deadlineData)
            throws DukeException {
        doneData = doneData.trim();
        deadlineData = deadlineData.trim();
        taskNameData = taskNameData.trim();
        if (taskNameData.isEmpty()) {
            throw new DukeException("todo");
        }
        if (doneData.isEmpty()) {
            throw new DukeException("missing details");
        }
        if (deadlineData.isEmpty()) {
            throw new DukeException("timing");
        }
        Deadline d = new Deadline(taskNameData, deadlineData);
        boolean completed = Integer.parseInt(doneData) == 1;
        d.setCompleted(completed);
        return d;
    }

    @Override
    public String toString() {
        String s;
        if (this.completed) {
            s = "[D]" + super.toString() + "(by: " + this.deadline + ")";
        } else {
            s = "[D]" + super.toString()  + " (by: " + this.deadline + ")";
        }
        return s;
    }
}
