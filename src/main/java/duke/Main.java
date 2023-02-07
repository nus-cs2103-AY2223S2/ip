package duke;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    private Duke duke = new Duke("/data.txt");

    @Override
    public void start(Stage primaryStage) throws Exception {
        duke.run();
    }
}
