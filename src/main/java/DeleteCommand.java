public class DeleteCommand extends Command {
    private int deleteIdx;
    
    public DeleteCommand(int deleteIdx) {
        this.deleteIdx = deleteIdx;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.delete(deleteIdx);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
