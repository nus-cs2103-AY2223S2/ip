import java.io.IOException;

import core.Miki;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import shigure.Cli;
import shigure.Gui;
import shigure.fxcontrol.MainWindow;
import task.Parser;

public class Init extends Application {
    private static boolean hasAsciiOnly = false;
    private static boolean hasNoAutoload = false;
    private static boolean hasCli = false;

    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("--ascii-only")) {
                hasAsciiOnly = true;
            }
            if (args[i].equals("--no-autoload")) {
                hasNoAutoload = true;
            }
            if (args[i].equals("--cli")) {
                hasCli = true;
            }
        }

        if (!hasCli) {
            Application.launch(Init.class, args);
        } else {
            Cli cli = new Cli(hasAsciiOnly);
            Miki miki = new Miki(cli, hasNoAutoload);
            String cmdLine = "";
            while (!Parser.isExitCommand(cmdLine)) {
                cmdLine = cli.readLine();
                miki.respond(cmdLine);
            }
        }
        Platform.exit();
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Init.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Miki");
            MainWindow mainWindow = fxmlLoader.getController();
            mainWindow.bindStage(stage);
            Gui gui = new Gui(mainWindow, stage, hasAsciiOnly);
            Miki miki = new Miki(gui, hasNoAutoload);

            fxmlLoader.<MainWindow>getController().setMiki(miki);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
