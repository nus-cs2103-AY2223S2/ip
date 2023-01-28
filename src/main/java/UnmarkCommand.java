public class UnmarkCommand extends Command {

    private Integer indexToUnmark;
    public UnmarkCommand(Integer index) {
        this.indexToUnmark = index;
    }


    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BotException {

        try {
            boolean success = tasks.mark(indexToUnmark, "unmark");
            if (success) {
                Ui.showUnmarked();
                storage.writeToFile(tasks);
            } else {
                Ui.showMarkFail();
            }
            Ui.print(tasks.getItem(indexToUnmark).getTaskInline());
        } catch (Exception e) {
            String markErr = "Sir! Index for toggling mark cannot be empty";
            throw new BotException(e.getMessage() + markErr);
        }

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
