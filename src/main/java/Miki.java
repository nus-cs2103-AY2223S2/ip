import command.Command;
import shigure.Ui;
import storage.Storage;
import task.Parser;
import task.TaskList;

import java.io.IOException;

public class Miki {
    public static void main(String[] args) {
        boolean hasAsciiOnly = false;
        boolean hasNoAutoload = false;
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("--ascii-only")) {
                hasAsciiOnly = true;
            }
            if (args[i].equals("--no-autoload")) {
                hasNoAutoload = true;
            }
        }

        Ui ui = new Ui(hasAsciiOnly);
        Storage storage = new Storage("./data/");
        TaskList tasks = new TaskList();
        ui.printIntro();

        if (!hasNoAutoload) {
            try {
                storage.load("autosave.txt", tasks);
            } catch (IOException | Storage.MikiLoadException ex) {

            }
        }

        String cmdLine = "";
        while (!Parser.isExitCommand(cmdLine)) {
            cmdLine = ui.readLine();
            Command cmd = Parser.parse(cmdLine);
            ui.printDiv();
            cmd.run(tasks, ui, storage);
            ui.printDiv();

            try {
                storage.save("autosave.txt", tasks);
            } catch (IOException ex) {

            }
        }
    }
}
