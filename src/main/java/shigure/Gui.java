package shigure;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.stage.Stage;
import javafx.util.Duration;
import shigure.fxcontrol.DialogBox;
import shigure.fxcontrol.MainWindow;

/**
 * A UI controller for Miki interactive GUI I/O.
 */
public class Gui extends Ui {
    private final MainWindow mainWindow;
    private final Stage stage;
    private final boolean isAsciiOnly;

    /**
     * Creates a new GUI.
     *
     * @param mainWindow <code>JavaFX</code> main window of the GUI.
     * @param stage <code>JavaFX</code> stage of the GUI.
     * @param isAsciiOnly whether this Cli shall operate in ASCII-only mode.
     */
    public Gui(MainWindow mainWindow, Stage stage, boolean isAsciiOnly) {
        this.mainWindow = mainWindow;
        this.stage = stage;
        this.isAsciiOnly = isAsciiOnly;
    }

    @Override
    public void printDiv() {
        // Do nothing
    }

    @Override
    public void printAutoDiv() {
        // Do nothing
    }

    @Override
    public void printUser(String s) {
        mainWindow.addDialog(s, DialogBox.Pov.USER);
    }

    @Override
    public void printMiki(String s) {
        mainWindow.addDialog(s, DialogBox.Pov.MIKI);
    }

    @Override
    public void printTasks(String[] tasks) {
        mainWindow.clearTasks();
        for (String t : tasks) {
            mainWindow.addTask(t);
        }
    }

    @Override
    public void refreshTasks(String[] tasks) {
        printTasks(tasks);
    }

    @Override
    public void printIntro() {
        String username = System.getProperty("user.name");
        if (!isAsciiOnly) {
            printMiki("Hello " + username + " !! Konmiki! \uFF3C(\uFFE3\u25BD\uFFE3)/");
        } else {
            printMiki("Hello " + username + " !! Konmiki! \\(^v^)/");
        }
    }

    @Override
    public void clearInput() {
        mainWindow.clearInput();
    }

    @Override
    public void close() {
        Timeline tl = new Timeline(new KeyFrame(Duration.millis(1500), ae -> stage.close()));
        tl.setCycleCount(1);
        tl.play();
    }
}
