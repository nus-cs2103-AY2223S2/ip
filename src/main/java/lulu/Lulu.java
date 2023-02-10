package lulu;

import lulu.command.Parser;
import lulu.command.Command;
import lulu.command.LoadCommand;

import lulu.exception.LuluException;

public class Lulu {
    private static final String NAME = "./data/lulu.txt";
    private TaskList tasks;
    private UI ui;
    private Storage storage;
    private boolean isSaveLoaded = false;

    public Lulu() {
        this.ui = new UI();
        this.tasks = new TaskList();
        this.storage = new Storage(NAME);
    }

    /**
     * This method runs the chatbot task manager.
     */
    /**
     public void run() {
     ui.showGreetText();
     if (storage.isSavePresent()) {
     Command c = new LoadCommand();
     c.execute(tasks, ui, storage);
     }
     boolean isExit = false;
     while (!isExit) {
     try {
     String fullCommand = ui.readCommand();
     Command c = Parser.parse(fullCommand);
     c.execute(tasks, ui, storage);
     isExit = c.isExit();
     } catch (LuluException e) {
     ui.showLine();
     System.out.println(e);
     ui.showLine();
     } catch (IndexOutOfBoundsException e) {
     ui.showLine();
     ui.showOutOfBounds();
     ui.showLine();
     }
     }
     }
     */

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String fullCommand) {
        if (!this.isSaveLoaded) {
            if (storage.isSavePresent()) {
                Command c = new LoadCommand();
                c.execute(tasks, ui, storage);
            }
            isSaveLoaded = true;
        }
        try {
            Command c = Parser.parse(fullCommand);
            return c.execute(tasks, ui, storage);
        } catch (LuluException e) {
            return ui.showContainer(e.toString());
        } catch (IndexOutOfBoundsException e) {
            return ui.showContainer(ui.showOutOfBounds());
        }
    }
}
