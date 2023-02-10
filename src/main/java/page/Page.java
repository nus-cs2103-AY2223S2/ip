package page;

/**
 * Represents a Page chatbot that can manage a list of tasks.
 */
public class Page {

    private Ui ui;
    private Storage storage;
    private QuestLog questLog;
    private Parser parser;

    /**
     * Constructs a Page object that saves/loads data from the given file path.
     *
     * @param filePath File path where the Page object saves/loads data.
     */
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

    /**
     * Runs the Page chatbot.
     */
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

    /*
    public String getResponse(String input) {
        boolean isBye = false;

        // ui.printGreeting();
        while (!isBye) {
            try {
                // String input = ui.readInput();
                isBye = parser.parseExecute(input, ui, storage, questLog);
            } catch (PageException e) {
                ui.printErrorMessage(e);
            }

        }
    }
    */

    /**
     * Starts the Page chatbot.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        Page page = new Page("data/questlog.txt");
        page.run();
    }
}

