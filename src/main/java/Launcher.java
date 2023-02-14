import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {

    /**
     * Launchers the main class.
     * @param args system args
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
