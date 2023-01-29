public class ListCommand extends Command {

    public ListCommand() {
        super();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String reply = tasks.print();
        ui.showMessageWithoutNewline(reply);
    }
}
