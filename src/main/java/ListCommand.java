public class ListCommand extends Command {
    ListCommand() {
        super("list");
    }

    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) {
        System.out.println(tl.toString());
    }
}
