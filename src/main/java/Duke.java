import command.Command;
import dukeexeption.DukeException;
import parser.Request;
import storage.LocalStorage;
import storage.TaskList;
import ui.Ui;

import java.util.Scanner;

public class Duke {
    private LocalStorage localTaskList;
    private TaskList tasks;
    private Ui ui;

    public Duke() {
        this.ui = new Ui();
        this.tasks = new TaskList();
    }

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

    public void run() {
        this.ui.printStartUpMessage();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String request = scanner.nextLine();
            if (request.equalsIgnoreCase("BYE")) {
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

    public static void main(String[] args) {
        new Duke("./data/tasks.txt").run();
    }
}
