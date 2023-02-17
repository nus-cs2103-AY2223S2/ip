import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * This method is called when the program is run to activate other parts of the code.
     * @param args System arguments.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
