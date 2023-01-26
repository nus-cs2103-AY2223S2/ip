public class AddCommand extends Command {
    private String command;

    public AddCommand(String command) {
        this.command = command;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task task = Task.makeTask(this.command);
        System.out.println(taskList.addTask(task));
        storage.save(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
