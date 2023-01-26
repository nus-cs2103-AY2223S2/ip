package duke;

import java.io.IOException;

public class Duke {
    private boolean isRunning;
    private final TaskList tasks;
    private final UiHandler ui;
    private final Storage storage;
    
    Duke() {
        this.storage = new Storage("./data/duke.txt");
        this.isRunning = true;
        this.tasks = this.storage.loadData();
        this.ui = new UiHandler();
    }
    
    public static void main(String[] args) throws IOException {
        Duke d = new Duke();
        d.ui.showStartingDialogue();
        
        while (d.isRunning) {
            d.isRunning = d.ui.run(d.tasks);
            d.storage.saveData(d.tasks);
        }
    }
}
