package meggy;

import javafx.application.Application;
import meggy.gui.MainApplication;

/** The class that statically launches the chatbot. */
public class Launcher {
    /** @deprecated Launcher class should not be initialized. */
    private Launcher() {
    }

    public static void main(String[] args) {
        Application.launch(MainApplication.class, args);
    }
}
