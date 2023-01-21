public class Duke {
    TaskList taskList;
    Storage storage;
    Ui ui;
    Parser parser;

    public Duke() {
        this.storage = new Storage();
        this.taskList = this.storage.read();
        this.ui = new Ui();
        this.parser = new Parser();
    }

    public void run() {
        ui.spawnBot();
        while (!parser.isBye) {
            parser.parser(taskList, storage, ui, ui.userCommand());
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}



