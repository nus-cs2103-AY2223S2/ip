import commands.Command;
import parser.Parser;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

public class Shao {

    private Ui ui;
    private Parser parser;
    private Storage storage;
    private TaskList tasklist;

    public static void main(String[] args) {
        new Shao().run(args);
    }

    private void initServices() {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage();
        tasklist = new TaskList();
    }

    /** Run the program until it terminates */
    public void run(String[] args) {
        initServices();

        storage.getFile(tasklist, parser, ui);

        ui.printRowDivider();
        ui.greetUser();
        ui.printRowDivider();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            Command c = parser.parseInput(fullCommand);
            ui.printRowDivider();
            c.execute(ui, parser, storage, tasklist);
            ui.printRowDivider();
            isExit = c.isExit();
        }
        ui.cleanUp();
    }

}
