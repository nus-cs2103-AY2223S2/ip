public class ListCommand extends Command {
    public static final String LIST_COMMAND = "list";

    public ListCommand() {
        super(LIST_COMMAND);
    }

    @Override
    public void execute(TaskList lst, Ui ui) {
        ui.showList(lst);
    }
}
