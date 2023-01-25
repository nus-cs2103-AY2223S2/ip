public class DeadlineCommand extends Command {
    private String content;

    public DeadlineCommand(String content) {
        this.content = content;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (!this.content.matches("^.+(\\s)/by(\\s).+$")) {
            throw new DukeException(
                    "Please use the correct format to add a deadline.");
        }
        String[] dlTask = content.split("/by");
        dlTask[0] = dlTask[0].trim();
        dlTask[1] = dlTask[1].trim();
        try {
            tasks.add(dlTask[0], dlTask[1], true);
        } catch (DukeException e) {
            throw e;
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
