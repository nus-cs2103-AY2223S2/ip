public class AddToDoCommand extends AddCommand {
    private String description;
    public AddToDoCommand(Ui ui, TaskList taskList, String description) {
        super(ui, taskList);
        this.description = description;
    }

    @Override
    public void execute() {
        this.taskList.add(new ToDo(description));
        super.printCreatedTaskStatus();
    }
}
