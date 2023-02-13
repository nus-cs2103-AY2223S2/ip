package sam.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sam.Sam;
import sam.command.Result;

/**
 * Handles user interaction.
 */
public class Ui {
    public static final String LOGO =
              " ██████╗ █████╗ ███╗   ███╗\n"
            + "██╔════╝██╔══██╗████╗ ████║\n"
            + "╚█████╗ ███████║██╔████╔██║\n"
            + " ╚═══██╗██╔══██║██║╚██╔╝██║\n"
            + "██████╔╝██║  ██║██║ ╚═╝ ██║\n"
            + "╚═════╝ ╚═╝  ╚═╝╚═╝     ╚═╝";
    public static final String USER =
              " ███████\n"
            + "████▀██▀█\n"
            + "████▄██▄█\n"
            + " ▀▀▀▀▀▀▀";
    public static final String SAM =
              "        ▄\n"
            + " ▒▒██▒▒▓▓▀\n"
            + "▒▒▀██▀▒▒▓▓\n"
            + " █▄██▄███▓▓\n"
            + "  ▀▀▀▀▀▀ ▓";

    private MainWindow mainWindow;


    /**
     * Sets up the application's stage.
     *
     * @param stage The stage to set up.
     */
    public void setStage(Stage stage) {
        mainWindow = new MainWindow(stage);
        respond(Dialog.GREETING.getDialog());
    }

    /**
     * Adds a dialogue from Sam formed by the given strings.
     *
     * @param messages A list of strings representing lines of dialogue.
     */
    public void respond(String... messages) {
        Result result = new Result();
        for (String message : messages) {
            result.addMessage(message);
        }
        respond(result);
    }

    public void respond(Result result) {
        VBox samDialog = result.getResult();
        mainWindow.addSamDialog(samDialog);
    }

    /**
     * Disables the input field and send button
     */
    public void disable() {
        mainWindow.disable();
    }
}
