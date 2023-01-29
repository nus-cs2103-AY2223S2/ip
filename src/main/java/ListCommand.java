public class ListCommand extends Command {
    public ListCommand() {}

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.showLine();
        ui.listText();
        int length = tasks.getSize();
        for (int i = 0; i < length; i++) {
            System.out.print(i+1);
            System.out.println(". " + tasks.get(i));
        }
        ui.showLine();
    }
}
