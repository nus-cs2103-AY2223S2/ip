package duke;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MainTest {

    private Main main;

    @BeforeEach
    public void start(Stage stage) {
        main = new Main();
        main.start(stage);
        assertNotNull(main);
    }

}
