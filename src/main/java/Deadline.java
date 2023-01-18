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
