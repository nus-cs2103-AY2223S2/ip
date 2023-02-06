package duke;

import duke.core.Core;
import duke.gui.ChatPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * <h1>Duke Task Management Application</h1>
 * Duke is an applications that allow the user to manage their task from the command line.
 *
 * @author Stanley Neoh Jia Jun
 */
public class Duke extends Application {
    private Core core;
    private ChatPane chatPane;
    private Scene scene;

    @Override
    public void start(Stage stage) {
        core = new Core();
        chatPane = new ChatPane((String cmd) -> {
            return core.respond(cmd);
        });
        chatPane.dukeSays(core.setup());

        scene = new Scene(chatPane);
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);
        stage.setScene(scene);
        stage.show();
    }
}
