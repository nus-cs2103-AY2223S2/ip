import io.Storage;
import io.Ui;
import task.TaskList;

public class Duke {

    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static final Engine ENGINE = new Engine();

    private final Storage<TaskList> storage;
    private final TaskList taskList;

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
        Ui.showReply("Current Tasks:\n" + this.taskList.toString());
    }

    public static void main(String[] args) {
        System.out.println(LOGO + "\nHello.\n");
        while (true) {
            if (!ENGINE.run())
                break;
        }
    }
}
