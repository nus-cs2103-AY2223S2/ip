public class PrintListCommand extends Command {
    public PrintListCommand() {}

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        System.out.println(tasks.toString());
    }

    @Override
    public boolean isExit() {
        return false;
    }
    
}
