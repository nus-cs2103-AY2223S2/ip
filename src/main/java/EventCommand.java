public class EventCommand extends Command {
    private String activity;
    private String from;
    private String to;

    public EventCommand(String cmd) {
        try {
            checkCommand(cmd);
            String c = cmd.split(" ")[0];
            int indexOfFrom = cmd.indexOf("/from ");
            int indexOfFromTime = indexOfFrom + 6;
            int indexOfTo = cmd.indexOf("/to ");
            int indexOfToTime = indexOfTo + 4;
            this.activity = cmd.substring(c.length() + 1, indexOfFrom - 1);
            this.from = cmd.substring(indexOfFromTime, indexOfTo - 1);
            this.to = cmd.substring(indexOfToTime);
        } catch (DukeException e) {
            System.out.println(e);
        }
    }

    public boolean execute(Storage tl, Ui ui, Storage storage) {
        Task t = new Event(activity, from, to);
        tl.addTask(t);
        System.out.println("Got it. I've added this task:\n" + t
                + "\n Now you have " + tl.getSize() + " tasks in the list.");
        return true;
    }
}
