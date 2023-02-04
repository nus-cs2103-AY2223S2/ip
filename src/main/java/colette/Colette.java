package colette;

import colette.command.Command;
import colette.exception.ColetteException;
import colette.gui.GuiText;
import colette.gui.MainWindow;
import colette.gui.SpriteEmotion;

/** Class that encapsulates the Colette chatbot */
public class Colette {

    /** Storage object to interact with storage */
    private Storage storage;
    /** TaskList object to store tasks */
    private TaskList tasks;
    /** UI object to display user interface and read user input */
    private GuiText guiText;

    private boolean isExit = false;
    private boolean isLoadSuccessful = true;

    /**
     * Constructs a Colette object with a specified path
     * to the data directory used for storing tasks.
     *
     * @param dirPath Relative path to the data directory
     *                used for storing tasks.
    */
    public Colette(String dirPath) {
        this.guiText = new GuiText();
        this.storage = new Storage(dirPath);
        assert this.guiText != null : "GuiText should not be null";
        assert this.storage != null : "Storage should not be null";
        try {
            this.tasks = new TaskList(storage.load());
            MainWindow.changeSpriteExpression(SpriteEmotion.HAPPY);
        } catch (ColetteException e) {
            this.isLoadSuccessful = false;
            this.tasks = new TaskList();
            MainWindow.changeSpriteExpression(SpriteEmotion.SAD);
        }
    }

    /**
     * Runs the given command.
     *
     * @param command Given command.
     * @return The text response.
     */
    public String runCommand(String command) {
        try {
            Command c = Parser.parseCommand(command);
            assert c != null : "Command should not be null";
            this.isExit = c.isExit();
            return c.execute(this.tasks, this.guiText, this.storage);
        } catch (ColetteException e) {
            MainWindow.changeSpriteExpression(SpriteEmotion.SAD);
            return this.guiText.showErrorMessage(e);
        }
    }

    public boolean isExit() {
        return this.isExit;
    }

    /**
     * Returns the text indicating whether
     * loading from storage was successful.
     *
     * @return Text indicating whether loading
     *         from storage was successful.
     */
    public String displayLoadFromStorageStatus() {
        return this.guiText.showLoadFromStorageStatus(isLoadSuccessful);
    }

}
