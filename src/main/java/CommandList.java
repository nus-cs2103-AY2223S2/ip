public class CommandList extends Command{
    public CommandList(String command) {
        super(command);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        for (int i = 0; i < taskList.getLength(); i++) {
            System.out.printf("%d.%s\n", i + 1, taskList.getTask(i).toString());
        }
        System.out.println();
    }
}
