package duke;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;

public class Duke {
    private final Parser parser;
    private final Storage storage;
    private final Ui ui;

    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.ui = new Ui();
        this.parser = new Parser(storage, ui);
    }

    public void run() {
        this.ui.printWelcomeMsg();
        boolean hasNext = true;
        while (hasNext) {
            String input = this.ui.readInput();
            hasNext = this.parser.parse(input);
        }
    }

    public static void main(String[] args) {
        new Duke("data.txt").run();
    }
}
