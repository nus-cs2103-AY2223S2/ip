import java.util.Scanner;
import command.Command;
import dukeexeption.DukeException;
import parser.Request;
import storage.LocalStorage;
import storage.TaskList;
import ui.Ui;

public class Duke {
    private final TaskList tasks;
    private final Ui ui;
    private LocalStorage localTaskList;

    public Duke(String filepath) {
        this.ui = new Ui();
        this.tasks = new TaskList();
        try {
            this.localTaskList = new LocalStorage(filepath);
            this.localTaskList.loadIntoProgramTaskList(this.tasks);
        } catch (DukeException error) {
            ui.printFormattedError(error);
        }
    }

    public static void main(String[] args) {
        new Duke("./data/tasks.txt").run();
    }

    public void run() {
        this.ui.printStartUpMessage();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String request = scanner.nextLine();
            if ("BYE".equalsIgnoreCase(request)) {
                if (this.localTaskList != null) {
                    this.localTaskList.writeFromProgramTaskList(this.tasks);
                }
                break;
            }
            try {
                Command command = new Request(request).parse();
                String reply = command.run(this.tasks);
                this.ui.printFormattedResponse(reply);
            } catch (DukeException error) {
                this.ui.printFormattedError(error);
            }
        }

        this.ui.printExitingMessage();
    }
}
