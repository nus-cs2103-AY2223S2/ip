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

    private int code;
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
        if (code == 0) return;
        if (code == 1) {
            taskList.list();
        } else if (code == 2) {
            taskList.markDone(index);
            storage.write();
        } else if (code == 3) {
            taskList.markUndone(index);
            storage.write();
        } else if (code == 4) {
            taskList.addTask(0, content);
            storage.write();
        } else if (code == 5) {
            taskList.addTask(1, content);
            storage.write();
        } else if (code == 6) {
            taskList.addTask(2, content);
            storage.write();
        } else if (code == 7) {
            taskList.delete(index);
            storage.write();
        }
    }

    protected boolean isExit() {
        return code == 0;
    }


}
