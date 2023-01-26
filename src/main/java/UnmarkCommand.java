public class UnmarkCommand extends Command {

    private int unmarkNumber;

    public UnmarkCommand(int unmarkNumber) {
        this.unmarkNumber = unmarkNumber;
    }

    public int getUnmarkNumber() {
        return this.unmarkNumber;
    }

    @Override
    public void execute(TaskList list, Storage store, Ui ui) throws DukeException {
        if (unmarkNumber < 1 || unmarkNumber > list.size()) {
            throw new DukeException("Sorry, this task number is invalid.");
        }
        list.get(this.unmarkNumber - 1).setStatus(false);
        store.save(list);
        ui.showMessage("OK, I've marked this task as not done yet:");
        ui.showMessage(list.get(unmarkNumber - 1).toString());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
