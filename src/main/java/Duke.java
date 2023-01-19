import command.Command;
import dukeexeption.DukeException;
import parser.Request;
import storage.LocalStorage;
import storage.TaskList;
import ui.Ui;

import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        LocalStorage localTaskList = new LocalStorage("./data/tasks.txt");

        Ui ui = new Ui();
        ui.printStartUpMessage();

        Scanner scanner = new Scanner(System.in);
        TaskList taskList = new TaskList();

        localTaskList.loadIntoProgramTaskList(taskList);

        while (true) {
            String request = scanner.nextLine();
            if (request.equalsIgnoreCase("BYE")) {
                localTaskList.writeFromProgramTaskList(taskList);
                break;
            }
            try {
                Command command = new Request(request).parse();
                String reply = command.run(taskList);
                ui.printFormattedResponse(reply);
            } catch (DukeException error) {
                ui.printFormattedError(error);
            }
        }

        ui.printExitingMessage();
    }
}
