import java.util.ArrayList;

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
        Commands commands = new Commands(lst, ui, storage);

        while (true) {
            String cmd = ui.readCMD();
            ui.printLine();
            Parser.parse(commands, cmd);
            ui.printLine();
            if (commands.getIsBye()) break;
        }
    }

    public static void main(String[] args) {
        new Duke("tasklist.txt").run();
    }
}
