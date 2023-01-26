public class DeleteCommand extends Command {
    int idx;
    public DeleteCommand(int idx) {
        this.idx = idx;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task cur = tasks.delete(this.idx - 1);
        String msg = "Noted. I've removed this task:\n";
        msg += cur + "\n";
        msg += "Now you have " + tasks.size() + " tasks in the list.";
        ui.show(msg);
    }
}
