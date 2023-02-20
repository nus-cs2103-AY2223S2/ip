package duke;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */

public class Launcher {

    /**
     * Launch Duke
     * @param args GUI if the user wants to run the GUI version of Duke
     *             Default (no args) is CLI version
     */

    public static void main(String[] args) {
        assert args != null;
        if (args.length > 0 && args[0].equals("GUI")) {
            Application.launch(Main.class, args);
        } else {
            Duke.main(args);
        }
    }
}
