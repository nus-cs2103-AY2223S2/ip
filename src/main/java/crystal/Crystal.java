package crystal;

import crystal.command.Command;

import java.io.File;

class Crystal {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Crystal(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.readFileContents());
        } catch (CrystalException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }


    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command cmd = Parser.parse(fullCommand);
                cmd.execute(tasks, ui, storage);
                storage.saveFile(tasks);
                isExit = cmd.isExit();
            } catch (CrystalException e) {
                ui.showError(e);

            }
        }
    }

    public static void main(String[] args) {
        String file2 = "/repos/Independentproject/myfiles/Crystal.txt";
        String base = "/repos/Independentproject";
        String relative = new File(base).toURI().relativize(new File(file2).toURI()).getPath();
        new Crystal(relative).run();
    }
}