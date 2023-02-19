package jane.javafx;

import jane.Jane;
import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Jane.class, args);
    }
}
