import java.util.Scanner;

public class Duke {
    protected static String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
    protected static TaskList lst;
    protected static boolean cont = true;
    protected static String path = "src/data/duke.txt";
    protected static Storage storage;
    protected final Parser parser;
    protected static Ui ui;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.parser = new Parser();
        try {
            this.lst = new TaskList();
            this.storage.loadFileInto(this.lst);
        } catch (DukeException e) {
            ui.showError(e.toString());
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while(!isExit) {
            try {
                String userInput = this.ui.readCommand();
                ui.showLine();
                Command command = parser.parse(userInput);
                command.execute(this.lst, this.ui);
                storage.saveToFile(this.lst);
                isExit = command.isExit();
            } catch (DukeException e) {
                ui.showError(e.toString());
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke(path).run();
    }
}
