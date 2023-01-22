public class AddCommand extends Command{
    protected String fullCommand;
    protected String[] s;
    public AddCommand(String fullCommand, String[] s) {
        this.fullCommand = fullCommand;
        this.s = s;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (s[0].equals("todo")){
            tasks.todo(s);
        } else if (s[0].equals("deadline")) {
            tasks.deadline(s);
        } else if (s[0].equals("event")) {
            tasks.event(s);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
