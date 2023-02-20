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
    private boolean isExit;

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
        this.isExit = false;
    }

    public boolean isExit() {
        return isExit;
    }

    public String showGreeting() {
        return ui.showGreeting();
    }

    public String getResponse(String input) {
        try {
            Command c = parser.parse(input);
            isExit = c.isExit();
            return c.execute(ui, storage, questLog);
        } catch (PageException e) {
            return ui.showErrorMessage(e);
        }
    }
}

