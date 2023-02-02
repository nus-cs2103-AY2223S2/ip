package duke;

import javafx.application.Application;

public class Duke {

    private final GuiHandler ui;

    Duke() {
        this.ui = new GuiHandler();
    }

    /**
     * Main function for running Duke.
     * @param args
     */
    public static void main(String[] args) {
        Duke d = new Duke();
        Application.launch(d.ui.getClass(), args);
    }
}
