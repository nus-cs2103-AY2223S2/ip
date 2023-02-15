package duncan;

import command.Command;

import gui.Gui;

import java.util.ArrayList;

public class Duncan {
    private Storage storage;
    private DuncanList duncanList;
    private DuncanList archive;
    private Ui ui;
    private Gui gui;

    public Duncan(String filePath, String archivePath, Gui gui) {
        this.ui = new Ui();
        this.storage = new Storage(filePath, archivePath, ui);
        this.duncanList = storage.retrieve("list");
        this.archive = storage.retrieve("archive");
        this.duncanList.setUi(ui);
        this.gui = gui;
    }

    public ArrayList<String> run(String stringCommand) {
        ui.clearStatements();
        assert ui.getStatements().size() == 0;
        Parser parser = new Parser(this.duncanList, this.archive, this.storage, this.ui);
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

