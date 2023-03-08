
import brotherbot.commands.*;
import brotherbot.exceptions.BroException;
import brotherbot.parser.Parser;
import brotherbot.storage.Storage;
import brotherbot.storage.TaskList;
import brotherbot.ui.Ui;

public class BrotherBot {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public BrotherBot(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        storage.load(this.ui);
        this.tasks = storage.getTasks();
    }

 ››   public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand, this.tasks);
                c.execute(this.tasks, this.ui);
                isExit = c.isExit;
                storage.save(c);
            } catch (BroException e) {
                ui.showError(e);
            }

        }
    }

    public static void main(String[] args) {
        new BrotherBot("data.txt").run();
    }






}

