public class DeadlineCommand extends Command {
    private String activity;
    private String date;
    private String time;

    public DeadlineCommand(String cmd) {
        try {
            checkCommand(cmd);
            String c = cmd.split(" ")[0];
            int indexOfBy = cmd.indexOf("/by ");
            int indexOfDate = indexOfBy + 4; // "/by "
            this.activity = cmd.substring(c.length() + 1, indexOfBy - 1);
            String datetime = cmd.substring(indexOfDate);
            this.date = datetime.split(" ")[0];
            this.time = datetime.split(" ")[1];
        } catch (DukeException e) {
            System.out.println(e);
        }
    }

    public boolean execute(Storage tl, Ui ui, Storage storage) {
        Task t = new Deadline(this.activity, this.date, this.time);
        tl.addTask(t);
        System.out.println("Got it. I've added this task:\n" + t
                + "\n Now you have " + tl.getSize() + " tasks in the list.");
        return true;
    }
}
