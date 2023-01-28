public class TodoCommand extends Command {
    private String activity;

    public TodoCommand(String cmd) {
        try {
            checkCommand(cmd);
            String c = cmd.split(" ")[0];
            this.activity = cmd.substring(c.length() + 1);
        } catch (DukeException e) {
            System.out.println(e);
        }
    }

    public boolean execute(Storage tl, Ui ui, Storage storage) {
        Task t = new Todo(this.activity);
        tl.addTask(t);
        System.out.println("Got it. I've added this task:\n" + t
                + "\n Now you have " + tl.getSize() + " tasks in the list.");
        return true;
    }

}
