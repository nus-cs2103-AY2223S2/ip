public class UnmarkCommand extends Command {
    private int id;

    public UnmarkCommand(String cmd) {
        this.id = Integer.parseInt(cmd.split(" ")[1]);
    }

    public boolean execute(Storage tl, Ui ui, Storage storage) {
        Task t = tl.getTask(this.id - 1);
        t.unmark();
        System.out.println("OK, I've marked this task as not done yet:\n" + t);
        return true;
    }
}
