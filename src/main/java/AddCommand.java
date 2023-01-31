public class AddCommand extends Command {
    private Task task;

    public AddCommand(CommandType type, Task task) {
        super(type);
        this.task = task;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        ui.printOutput("Got it. I've added this task:\n\t\t" + task.toString() + "\n\t Now you have " + list.getSize() + " tasks in the list.");
        list.addTask(task);
        storage.saveListToFile(list, ui);
    }
}
