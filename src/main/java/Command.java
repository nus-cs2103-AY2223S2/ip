import java.io.IOException;

public class Command {
    /*
    Command codes:
    -1 - Invalid input.
    0 - Bye
    1 - list
    2 - mark
    3 - unmark
    4 - to_do
    5 - deadline
    6 - event
    7 - delete
     */

    private final int code;
    private int index;
    private String[] content;

    protected Command(int code) {
        this.code = code;
    }

    protected Command(int code, int index) {
        this.code = code;
        this.index = index;
    }

    protected Command(int code, String[] content) {
        this.code = code;
        this.content = content;
    }

    protected void execute(Ui ui, TaskList taskList, Storage storage) throws DukeException, IOException {
        if (code == -1) return;
        if (code == 0) {
            ui.byeMessage();
        } else if (code == 1) {
            taskList.list();
            ui.showLine();
        } else if (code == 2) {
            Task t = taskList.markDone(index);
            ui.markDoneMessage(t);
            storage.write();
        } else if (code == 3) {
            Task t = taskList.markUndone(index);
            ui.markUndoneMessage(t);
            storage.write();
        } else if (code == 4) {
            Task t = taskList.addTask(0, content);
            ui.addMessage(t, taskList);
            storage.write();
        } else if (code == 5) {
            Task t = taskList.addTask(1, content);
            ui.addMessage(t, taskList);
            storage.write();
        } else if (code == 6) {
            Task t = taskList.addTask(2, content);
            ui.addMessage(t, taskList);
            storage.write();
        } else if (code == 7) {
            Task t = taskList.delete(index);
            ui.deleteMessage(t, taskList);
            storage.write();
        }
    }

    protected boolean isExit() {
        return code == 0;
    }


}
