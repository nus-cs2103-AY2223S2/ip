import command.Command;
import command.Error;
import io.Storage;
import io.Ui;
import task.TaskList;

public class Duke {
    private final Storage<TaskList> storage;
    private final TaskList taskList;
    private boolean isExit;

    public Duke(String filename) {
        this.storage = Storage.of(TaskList.class, filename);
        this.taskList = this.storage.load().match(
                taskList -> taskList,
                error -> {
                    switch (error) {
                        case FILE_NOT_FOUND:
                            return new TaskList();
                        case IO_ERROR:
                        case CAST_ERROR:
                            Ui.showReply("Error loading tasks, tasks have been reset.");
                            return new TaskList();
                        default:
                            return new TaskList();
                    }
                });
        this.isExit = false;
        Ui.showReply("Current Tasks: " + this.taskList.toString());
    }

    private void run() {
        while (!this.isExit) {
            String input = Ui.getInput();
            Command command = Command.parser().parse(input).match(
                    pr -> pr.first(),
                    msg -> Error.of(msg));
            command.execute(this.taskList, this.storage);
            this.isExit = command.isExit();
        }
    }

    public static void main(String[] args) {
        Ui.showWelcome();
        Duke duke = new Duke("taskList.ser");
        duke.run();
    }
}
