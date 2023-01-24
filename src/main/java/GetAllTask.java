public class GetAllTask implements Command {

    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) {
        ui.showGetAllTaskResult(tl);
    }
}
