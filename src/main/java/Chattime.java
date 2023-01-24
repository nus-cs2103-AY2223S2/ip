import chattime.command.Command;
import chattime.exception.ChattimeException;
import chattime.parser.Parser;
import chattime.storage.Storage;
import chattime.task.TaskList;
import chattime.ui.Ui;

import java.util.Scanner;

public class Chattime {

    private Storage storage;
    private Ui ui;
    private TaskList taskList;

    public Chattime(String filePath) {
        ui = new Ui();
        storage = new Storage(ui, filePath);
        taskList = new TaskList(storage.loadData());
    }

    public void run() {

        Scanner sc = new Scanner(System.in);
        String userInput;
        while (ui.getExitStatus()) {
            userInput = sc.nextLine();
            Parser parser = new Parser(userInput);
            try {
                Command cmd = parser.parse();
                if (cmd != null) {
                    cmd.execute(ui, taskList, storage);
                }
            } catch (ChattimeException e) {
                ui.printError(e.getMessage());
            }
        }

    }


    public static void main(String[] args) {
        new Chattime("").run();
    }

}
