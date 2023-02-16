package commands;

/**
 * Represents a <code>Command</code> that when executed undoes the most recent command in history.
 */
public class UndoCommand extends Command {


    @Override
    public String execute() {

        if (isHistoryEmpty()) {
            return "Nothing to undo";
        }
        this.history.removeFirst().undo();
        try {
            storage.saveTasks(taskList.getArrayListCopy());
        } catch (Exception e) {
            return e.getMessage();
        }

        return toResultString();
    }

    private boolean isHistoryEmpty() {
        return this.history.isEmpty();
    }

    @Override
    String toResultString() {
        return "Successfully Undo action:";
    }


}
