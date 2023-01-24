
import java.io.FileWriter;
import java.io.IOException;
import java.lang.StringBuilder;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


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
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                storage.saveFile(tasks);
                isExit = c.isExit();
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