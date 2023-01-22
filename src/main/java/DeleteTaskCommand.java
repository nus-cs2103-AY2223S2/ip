public class DeleteTaskCommand extends Command {
    private String indexString;
    public DeleteTaskCommand(String command) {
        super();
        this.indexString = command;
    }

    @Override
    boolean isExit() {
        return false;
    }

    @Override
    void execute(TaskList taskList,Ui ui, Storage storage) {
        try {
            taskList.deleteTask(this.indexString);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }

    }
}
