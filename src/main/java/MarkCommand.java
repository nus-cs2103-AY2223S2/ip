public class MarkCommand extends Command {
    private int id;

    public MarkCommand(String cmd) {
        this.id = Integer.parseInt(cmd.split(" ")[1]);
    }

    public boolean execute(Storage tl, Ui ui, Storage storage) {
        Task t = tl.getTask(this.id - 1);
        t.mark();
        System.out.println("Nice! I've marked this task as done:\n" + t);
        return true;
    }
}
