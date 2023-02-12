import command.Command;
import command.Error;
import io.Gui;
import io.Storage;
import io.Ui;
import task.TaskList;

/**
 * Main class
 */
public class Duke {
    private final Storage<TaskList> storage;
    private final TaskList taskList;
    private final Ui ui;
    private boolean isExit;

    /**
     * @param filename Name of file to save tasklist to
     */
    public Duke(String filename) {
        this.storage = Storage.of(TaskList.class, filename);
        this.ui = new Gui().launch();
        this.taskList = this.storage.load().match(
                taskList -> taskList,
                error -> {
                    switch (error) {
                        case FILE_NOT_FOUND:
                            return new TaskList();
                        case IO_ERROR:
                        case CAST_ERROR:
                            ui.showReply("Error loading tasks, tasks have been reset.");
                            return new TaskList();
                        default:
                            return new TaskList();
                    }
                });
        this.isExit = false;
        ui.showReply("Current Tasks: " + this.taskList.toString());
    }

    private void run() {
        while (!this.isExit) {
            String input = ui.getInput();
            Command command = Command.parser().parse(input).match(
                    pr -> pr.first(),
                    msg -> Error.of(msg));
            command.execute(this.taskList, this.ui, this.storage);
            this.isExit = command.isExit();
        }
    }

    /**
     * Main function
     */
    public static void main(String[] args) {
        Duke duke = new Duke("taskList.ser");
        duke.run();
    }
}
