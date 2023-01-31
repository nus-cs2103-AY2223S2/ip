import command.Command;
import shigure.Ui;
import storage.Storage;
import task.Parser;
import task.TaskList;

import java.io.IOException;

/**
 * Main class of the Miki personal-task manager project.
 * In honor of Fuzuki Miki.
 */
public class Miki {
    /**
     * Main function of the Miki project.
     *
     * @param args command-line arguments from the initial program call.
     */
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
            } catch (IOException ex) {

            } catch (Storage.MikiLoadException ex) {
                ui.print("the autosave is corrupted... not using that!");
                ui.printDiv();
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
