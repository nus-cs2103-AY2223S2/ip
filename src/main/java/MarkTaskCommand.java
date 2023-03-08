public class MarkTaskCommand extends Command{

    public MarkTaskCommand(String input) {
        super(input);
    }

    public void execute(TaskList storage, Ui ui) {
        int i = Integer.parseInt(input.substring(5)) - 1;
        storage.mark(i);
        ui.toUser("Marked as you wish my brother:");
        ui.toUser((i + 1) + ". " + storage.getPrintFormat(i));
    }
}
