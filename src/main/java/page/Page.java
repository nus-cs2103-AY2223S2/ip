package page;

public class Page {

    private Ui ui;
    private Storage storage;
    private QuestLog questLog;
    private Parser parser;


    public Page(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.questLog = new QuestLog(storage.loadData());
        } catch (PageException e) {
            ui.printErrorMessage(e);
            this.questLog = new QuestLog();
        }

        this.parser = new Parser();
    }

    public void run() {
        boolean isBye = false;

        ui.printGreeting();
        while (!isBye) {
            try {
                String input = ui.readInput();
                isBye = parser.parseExecute(input, ui, storage, questLog);
            } catch (PageException e) {
                ui.printErrorMessage(e);
            }

        }
    }

    public static void main(String[] args) {
        Page page = new Page("data/questlog.txt");
        page.run();
    }
}

