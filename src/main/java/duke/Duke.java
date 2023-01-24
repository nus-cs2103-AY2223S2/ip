package duke;

import duke.taskType.TaskList;
import duke.commands.*;
import duke.*;
public class Duke {
    private TaskList lst;
    private Ui ui;
    private Storage storage;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.lst = storage.load();
    }

    public void run() {
        ui.hello();

        while (true) {
            String cmd = ui.readCMD();
            ui.printLine();
            Command command = Parser.parse(cmd);
            command.operate(lst, ui, storage);
            ui.printLine();
            System.out.println();
            if (command.isBye()) break;
        }
    }

    public static void main(String[] args) {
        new Duke("tasklist.txt").run();
    }
}
