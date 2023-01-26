public class MarkCommand extends Command {

    private int markNumber;

    public MarkCommand(int markNumber) {
        this.markNumber = markNumber;
    }

    @Override
    public void execute(TaskList list, Storage store, Ui ui) throws DukeException {
        if (markNumber < 1 || markNumber > list.size()) {
            throw new DukeException("Sorry, this task number is invalid.");
        }
        list.get(this.markNumber - 1).setStatus(true);
        store.save(list);
        ui.showMessage("Nice! I've marked this task as done:");
        ui.showMessage(list.get(markNumber - 1).toString());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
