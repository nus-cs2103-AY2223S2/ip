package duke;

import command.Command;

import gui.Gui;

import java.util.ArrayList;

public class Duke {
    private Storage storage;
    private DukeList dukeList;
    private DukeList archive;
    private Ui ui;
    private Gui gui;

    public Duke (String filePath, String archivePath, Gui gui) {
        this.ui = new Ui();
        this.storage = new Storage(filePath, archivePath, ui);
        this.dukeList = storage.retrieve("list");
        this.archive = storage.retrieve("archive");
        this.dukeList.setUi(ui);
        this.gui = gui;
    }

    public ArrayList<String> run(String stringCommand) {
        ui.clearStatements();
        assert ui.getStatements().size() == 0;
        Parser parser = new Parser(this.dukeList, this.archive, this.storage, this.ui);
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

