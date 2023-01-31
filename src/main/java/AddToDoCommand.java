public class AddToDoCommand extends AddCommand {
    private String description;
    public AddToDoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(Ui ui, TaskList taskList, Storage storage) {
        taskList.add(new ToDo(description));
        super.printCreatedTaskStatus(taskList, ui);
    }
}
