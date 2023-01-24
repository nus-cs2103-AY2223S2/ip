public class UnmarkCommand extends Command {

    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList list) {
        Task task = list.get(index - 1);
        task.unmarkAsDone();
        UI.echo("OK, I've marked this task as not done yet:\n  " + task);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof UnmarkCommand)) {
            return false;
        }
        UnmarkCommand cmd = (UnmarkCommand) obj;
        return index == cmd.index;
    }
}
