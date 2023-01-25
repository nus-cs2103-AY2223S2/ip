import java.io.IOException;

public class Command {
    /*
    Command codes:
    0 - Bye
    1 - list
    2 - mark
    3 - unmark
    4 - add
    5 - delete
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
            taskList.addTask(content);
            storage.write();
        } else if (code == 5) {
            taskList.delete(index);
            storage.write();
        }
    }

    protected boolean isExit() {
        return code == 0;
    }


}
