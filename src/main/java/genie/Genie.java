package genie;

import genie.command.Command;
import genie.exception.DukeException;

import java.io.*;

/**
 * The main class for Genie, a Command-line Interface bot. Primarily functions as a task planner that responds to
 * and recognises user commands.
 */
public class Genie {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    public Genie() {
        ui = new Ui();
        storage = new Storage();
        try {
            tasks = storage.loadData();
        } catch (IOException e) {
            System.out.println(ui.showErrorMessage() + "\n" + e);
        }
    }

    /**
     * This driver creates the Genie bot.
     * @param args an array of command-line arguments
     */
    public static void main(String[] args) {
        new Genie().activate();
    }

    /**
     * Starts up the Genie bot on the command-line interface.
     */
    public void activate() {
        ui.bootLogo();
        ui.greet();
        boolean isExit = false;
        ui.printLoadedTaskList(storage.getLoadedTaskList());
        while (!isExit) {
            try {
                String i = ui.readCommand();
                ui.printLine();
                Parser parser = new Parser();
                Command c = parser.parse(i);
                c.execute(tasks, ui, storage);
                isExit = c.isExitCommand();
            } catch (IOException e) {
                System.out.println(ui.showErrorMessage() + "\n" + e);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
