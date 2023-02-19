package duke.ui;

/**
 * Graphical implementation of the user interface.
 */
public class GraphicalUi implements Ui {
    private MainWindow mainWindow;

    /**
     * Creates the Graphical UI.
     *
     * @param mainWindow The main window's controller.
     */
    public GraphicalUi(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    /**
     * Displays text on the GUI.
     *
     * @param text The text to be displayed.
     */
    @Override
    public void showText(String text) {
        mainWindow.showReply(text);
    }

    /**
     * This method does not display anything on the GUI.
     */
    @Override
    public void showLine() { /* Don't show line on GUI */ }

    /**
     * Displays startup text on the GUI.
     */
    @Override
    public void showStartup() {
        mainWindow.showReply("Hello Sir! How can I help you?");
    }
}
