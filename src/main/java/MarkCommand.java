public class MarkCommand extends Command {
    int idx;

    public MarkCommand(int idx) {
        this.idx = idx;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task cur = tasks.mark(this.idx - 1);
        String msg = "Nice! I've marked this task as done:\n";
        msg += cur;
        ui.show(msg);
    }
}
