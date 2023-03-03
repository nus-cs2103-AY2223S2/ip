package duke;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;

import javafx.stage.Stage;


public class MainTest {

    private Main main;

    @BeforeEach
    public void start(Stage stage) {
        main = new Main();
        main.start(stage);
        assertNotNull(main);
    }

}
