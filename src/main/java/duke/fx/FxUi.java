package duke.fx;

/**
 * Graphical UI.
 */
public class FxUi {
    private MainWindow mainWindow;

    /**
     * Creates FxUi.
     *
     * @param mainWindow Main window's controller.
     */
    public FxUi(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    /**
     * Displays text on the GUI.
     *
     * @param text The text to be displayed.
     */
    public void displayText(String text) {
        mainWindow.displayReply(text);
    }

    /**
     * Displays startup text on the GUI.
     */
    public void displayIntro() {
        mainWindow.displayReply("Welcome to MEL! Type 'help' to get help");
    }
}
