package src.main.c4po;


public class MarkCommand extends Command {

    Integer indexToMark;

    public MarkCommand(Integer index) {
        indexToMark = index;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BotException {
        try {
            boolean success = tasks.mark(indexToMark, "mark");
            if (success) {
                Ui.showMarkedDone();
                //override storage file with all new tasks
                storage.writeToFile(tasks);
            } else {
                Ui.showMarkFail();
            }

            Ui.print(tasks.getItem(indexToMark).getTaskInline());

        } catch (Exception e) {
            String markErr = "Sir! Index for toggling mark cannot be empty";
            throw new BotException(markErr);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
