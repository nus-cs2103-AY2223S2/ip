package page;

import page.command.Command;

/**
 * Represents a Page chatbot that can manage a list of tasks.
 */
public class Page {

    private Parser parser;
    private Ui ui;
    private Storage storage;
    private QuestLog questLog;

    /**
     * Constructs a Page object that saves/loads data from the given file path.
     *
     * @param filePath File path where the Page object saves/loads data.
     */
    public Page(String filePath) {
        this.parser = new Parser();
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.questLog = new QuestLog(storage.loadData());
        } catch (PageException e) {
            System.out.println(ui.showErrorMessage(e));
            this.questLog = new QuestLog();
        }
    }

    /**
     * Runs the Page chatbot.
     */
    public void run() {
        boolean isExit = false;

        System.out.println(ui.showGreeting());
        while (!isExit) {
            try {
                String input = ui.readInput();
                Command c = parser.parse(input);
                System.out.println(c.execute(ui, storage, questLog));
                isExit = c.isExit();
            } catch (PageException e) {
                System.out.println(ui.showErrorMessage(e));
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

