package duke;

import command.Command;

import gui.Gui;

import java.util.ArrayList;

public class Duke {
    private Storage storage;
    private DukeList dukeList;
    private Ui ui;
    private Gui gui;

    public Duke (String filePath, Gui gui) {
        this.ui = new Ui();
        this.storage = new Storage(filePath, ui);
        this.dukeList = storage.retrieveList(ui);
        this.dukeList.setUi(ui);
        this.gui = gui;
    }

    public ArrayList<String> run(String stringCommand) {
        ui.clearStatements();
        Parser parser = new Parser(this.dukeList, this.storage, this.ui);
        boolean isExit;
        try {
            Command c = parser.parse(stringCommand);
            c.execute();
            isExit = c.isExit();
            if (isExit) {
                gui.close();
            }
            return ui.getStatements();
        } catch (Exception e) {
            ui.addStatement(e.getMessage());
        }
        return ui.getStatements();
    }
}

