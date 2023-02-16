package iris;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * starts the program
     * @param args dunno
     */
    public static void main(String[] args) {
        try {
            Application.launch(Main.class, args);
        } catch (Exception e) {
            System.out.println(e.getCause().getClass());
            System.out.println(e.getClass());
        }
    }
}
